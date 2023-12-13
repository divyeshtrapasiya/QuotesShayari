package com.example.quotesshayari.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesshayari.Model.CatagoryModel
import com.example.quotesshayari.R

class CatagoryAdapter(context: Context,var catagorylist: ArrayList<CatagoryModel>,var onitemclick : ((id:Int)->Unit)) : RecyclerView.Adapter<CatagoryAdapter.MyviewHolder>(){
    class MyviewHolder( v : View) : RecyclerView.ViewHolder(v) {

        var txtshayari : TextView=v.findViewById(R.id.txtcatagoryshayari)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.catagory_item,parent,false)
        var holder = MyviewHolder(v)

        return holder
    }

    override fun getItemCount(): Int {
        return catagorylist.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.txtshayari.text = catagorylist.get(position).catagory

        holder.txtshayari.setOnClickListener{
            onitemclick.invoke(catagorylist.get(position).id)
        }

        Log.e("TAG", "onBindViewHolder: "+holder.txtshayari)
    }
}