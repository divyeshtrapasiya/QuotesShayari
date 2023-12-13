package com.example.quotesshayari.Adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesshayari.EditActivity
import com.example.quotesshayari.Model.QuotesModel
import com.example.quotesshayari.R


class QuotesAdapter(var context: Context, var quoteslist: ArrayList<QuotesModel>,var like: (Int, Int) -> Unit,var shayariselect: (shayari :String ) -> Unit) :
    RecyclerView.Adapter<QuotesAdapter.MyviewHolder>() {
    class MyviewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var txtshayari: TextView = v.findViewById(R.id.txtshayari)
        var imgbtnshare: ImageButton = v.findViewById(R.id.imgbtnshare)
        var imgbtncopy: ImageButton = v.findViewById(R.id.imgbtncopy)
        var imgbtnedit: ImageButton = v.findViewById(R.id.imgbtnedit)
        var imglike: ImageView = v.findViewById(R.id.imglike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        context = parent.context
        var v = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item, parent, false)
        var holder = MyviewHolder(v)
        return holder
    }

    override fun getItemCount(): Int {

        return quoteslist.size

    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {

        holder.txtshayari.text = quoteslist.get(position).quotes

        holder.imgbtnshare.setOnClickListener {

            val s = Intent(Intent.ACTION_SEND)
            s.type = "text/plain"
            s.putExtra(Intent.EXTRA_TEXT, quoteslist[position].quotes)
            Log.e("TAG", "onBindViewHolder: " + quoteslist.get(position).quotes)
            context.startActivity(s)

        }

        holder.imgbtncopy.setOnClickListener {

            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", holder.txtshayari.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "text copy", Toast.LENGTH_SHORT).show()
        }

        holder.imgbtnedit.setOnClickListener {

            shayariselect.invoke(quoteslist[position].quotes)
            var i = Intent(holder.imgbtnedit.context,EditActivity::class.java)
            i.putExtra("id",quoteslist[position].id)
            i.putExtra("shayari",quoteslist[position].quotes)
            holder.imgbtnedit.context.startActivity(i)
        }


        holder.imglike.setOnClickListener{

            if (quoteslist[position].status == 0) {

                holder.imglike.setImageResource(R.drawable.redlike)
                like.invoke(quoteslist[position].id,1)

            } else {
                holder.imglike.setImageResource(R.drawable.like)
                like.invoke(quoteslist[position].id,0)

            }

        }

        holder.imglike.setOnClickListener {


            if (quoteslist[position].status == 1) {

                like.invoke(quoteslist[position].id, 0)
                holder.imglike.setImageResource(R.drawable.like)
                quoteslist[position].status = 0
            } else {
                like.invoke(quoteslist[position].id, 1)
                holder.imglike.setImageResource(R.drawable.redlike)
                quoteslist[position].status = 1
            }


        }
    }
}

