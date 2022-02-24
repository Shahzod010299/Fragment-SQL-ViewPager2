package com.example.fragmentviewpagersqltablayout.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import com.example.fragmentviewpagersqltablayout.R
import com.example.fragmentviewpagersqltablayout.db.DbManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_user.*
import org.w3c.dom.CharacterData

class AddUserFragment : BaseAdapterFragment(R.layout.fragment_add_user) {
    override fun initialize() {


        val unicode = 0x1F389
        text_dastruchi.text = String(Character.toChars(unicode)).plus("  Dasturchi: Shahzod Toshboyev")

        val dbManager = DbManager(requireActivity())

        btn_save.setOnClickListener {
            val name: String = input_name.text.toString()
            val password: String = input_password.text.toString()

            dbManager.openDB()

            dbManager.insertDB(name,password)

            snackBar()
        }


    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun snackBar() {
        val snackBar = Snackbar.make(view!!.rootView,"Saqalandi",Snackbar.LENGTH_LONG).setAction("Action",null)
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