package com.example.quotesshayari.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesshayari.Model.FavotiteModel
import com.example.quotesshayari.R

class FavoriteAdapter(var like: (Int, Int) -> Unit) :
    RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    var list = ArrayList<FavotiteModel>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtshayari: TextView = view.findViewById(R.id.txtshayari)
        var imglike: ImageView = view.findViewById(R.id.imglike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtshayari.text = list[position].Shayari_item

        holder.imglike.setImageResource(R.drawable.redlike)

        holder.imglike.setOnClickListener {
            like.invoke(list[position].Shayari_id, 0)
            holder.imglike.setImageResource(R.drawable.like)
            list[position].fav = 0
            deleteItem(position)
        }
    }

    fun updateList(list: ArrayList<FavotiteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }

}