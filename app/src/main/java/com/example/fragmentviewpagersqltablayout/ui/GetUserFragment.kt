package com.example.fragmentviewpagersqltablayout.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentviewpagersqltablayout.R
import com.example.fragmentviewpagersqltablayout.adapter.UserAdapter
import com.example.fragmentviewpagersqltablayout.db.DbManager
import com.example.fragmentviewpagersqltablayout.models.UserData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_add_user.view.*
import kotlinx.android.synthetic.main.fragment_get_user.*

class GetUserFragment : BaseAdapterFragment(R.layout.fragment_get_user) {
    val data = ArrayList<UserData>()
    private lateinit var adapter: UserAdapter

    override fun onResume() {
        super.onResume()
        setRecyclerView()
    }

    override fun initialize() {

        text_get_user.setOnClickListener {

            if (search_view.visibility == View.GONE) {
                search_view.visibility = View.VISIBLE
            } else {
                search_view.visibility = View.GONE
            }

        }
        searchInfo()

    }

    private fun searchInfo() {

        val search: SearchView = view?.findViewById(R.id.search_view)!!

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                adapter.notifyDataSetChanged()
                return false
            }

        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecyclerView() {

        val my_rec = activity?.findViewById<RecyclerView>(R.id.recycler_view)


        val dbManager = DbManager(requireActivity())
        dbManager.openDB()

        val list = dbManager.readData()

        data.clear()

        (0..list.size - 1).forEach { i ->
            data.add(UserData(list[i].user_id, list[i].user_name, list[i].user_password))
        }

        text_get_user.text = "Get Users  All Users size =" +  data.size.toString()

        my_rec?.layoutManager = LinearLayoutManager(requireActivity())

        adapter = UserAdapter(data)
        my_rec?.adapter = adapter


        adapter.setOnClickItem { itemPos , boolEdDel->

            if (boolEdDel){
                customDialog(list,itemPos, dbManager)
            }else{
                dbManager.deletID(list[itemPos].user_id)
            }

            onResume()

        }

        adapter.notifyDataSetChanged()
    }

    private fun customDialog(
        list: ArrayList<UserData>,
        itemPos: Int,
        dbManager: DbManager
    ) {
        val dialog= Dialog(requireActivity())

        val dialogLayout =layoutInflater.inflate(R.layout.custom_dialog,null)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogLayout)

        Log.d("key_user_id",list[itemPos].user_id.toString())
        dialogLayout.text_id.setText("user id = "+list[itemPos].user_id.toString())

        dialogLayout.input_name_new.setText(list[itemPos].user_name.toString())
        dialogLayout.input_password_new.setText(list[itemPos].user_password.toString())

        dialogLayout.btn_cancele.setOnClickListener {
            dialog.dismiss()
        }

        dialogLayout.btn_edit.setOnClickListener {
            val new_name: String = dialogLayout.input_name_new.text.toString()
            val new_password: String = dialogLayout.input_password_new.text.toString()

            dbManager.updateDB(
                list[itemPos].user_id,
                new_name,
                new_password,
            )

            snackBar()
            dialog.dismiss()
        }


        dialog.show()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun snackBar() {
        val snackBar = Snackbar.make(view!!.rootView,"O'zgartirildi", Snackbar.LENGTH_LONG).setAction("Action",null)
        val snackBarView = snackBar.view

        snackBarView.setBackgroundColor(Color.GREEN)

        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.setTextSize(18F)
        snackBar.show()

    }

    override fun onDestroy() {
        super.onDestroy()
        val dbManager = DbManager(requireActivity())
        dbManager.closeDB()
    }
}