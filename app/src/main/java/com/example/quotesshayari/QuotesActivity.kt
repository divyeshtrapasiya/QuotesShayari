package com.example.quotesshayari

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.externaldatabase.DatabaseHelper
import com.example.quotesshayari.Adapter.QuotesAdapter
import com.example.quotesshayari.Model.QuotesModel
import com.example.quotesshayari.databinding.ActivityQuotesBinding

class QuotesActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuotesBinding
    var quoteslist = ArrayList<QuotesModel>()
    lateinit var db: DatabaseHelper
    lateinit var myadapter : QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()

    }

    private fun initview() {

        db = DatabaseHelper(this)

        val id = intent.getIntExtra("id", 0)

        if (id == 1) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Happy"
        }
        if (id == 2) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Sad"
        }
        if (id == 3) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Life"
        }
        if (id == 4) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Love"
        }
        if (id == 5) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Friend"
        }
        if (id == 6) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "My Life"
        }
        if (id == 7) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Money"
        }
        if (id == 8) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "School Life"
        }
        if (id == 9) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Brother"
        }
        if (id == 10) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Sister"
        }
        if (id == 11) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Collage"
        }
        if (id == 12) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Funny"
        }
        if (id == 13) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Motivation"
        }
        if (id == 14) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Leadership"
        }
        if (id == 15) {
            db.Quotesdisplay(id)
            binding.txtallquotes.text = "Success"
        }

        quoteslist = db.Quotesdisplay(id)

        myadapter = QuotesAdapter(this, quoteslist,shayariselect = {}, like = {id,status ->

            db.UpdeteRecord(id,status)

            Log.e("TAG", "update: "+id+"  "+status)

        })

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleview.layoutManager = layoutManager

        binding.recycleview.adapter = myadapter


    }
}