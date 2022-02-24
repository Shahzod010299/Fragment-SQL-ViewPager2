package com.example.fragmentviewpagersqltablayout.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentviewpagersqltablayout.R
import com.example.fragmentviewpagersqltablayout.models.UserData
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception

class UserAdapter(val data: ArrayList<UserData>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {

    private var listener: ((Int,Boolean) -> Unit)? = null

    var tempList = data

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.apply {
            text_user_id.text = tempList[position].user_id.toString()
            text_user_name.text = tempList[position].user_name
            text_user_password.text = tempList[position].user_password
        }

        holder.itemView.card_edit.setOnClickListener {
            listener?.invoke(position,true)

        }

        holder.itemView.card_delete.setOnClickListener {
            listener?.invoke(position,false)
        }


    }

    override fun getItemCount(): Int {
        return tempList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(searchInfo: CharSequence?): FilterResults {
                var filterList = ArrayList<UserData>()

                if (searchInfo!!.isEmpty() || searchInfo.length == 0){
                    filterList = data
                }else{
                    data.forEach { dataItem ->

                        if (dataItem.user_name.toString().lowercase().contains(searchInfo.toString().lowercase())){
                            filterList.add(dataItem)
                        }

                    }
                }

                val filterResult = FilterResults()
                filterResult.values = filterList
                return filterResult

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, result: FilterResults?) {
                try {
                    tempList = result!!.values as ArrayList<UserData>
                }catch (e: Exception){

                }
                notifyDataSetChanged()
            }

        }
    }

    fun setOnClickItem(f: (Int,Boolean) -> Unit){
        listener = f
    }


}