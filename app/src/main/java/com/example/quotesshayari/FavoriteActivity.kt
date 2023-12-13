package com.example.quotesshayari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.externaldatabase.DatabaseHelper

import com.example.quotesshayari.Adapter.FavoriteAdapter
import com.example.quotesshayari.Model.FavotiteModel
import com.example.quotesshayari.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {

    lateinit var db : DatabaseHelper
    var list = ArrayList<FavotiteModel>()
    lateinit var adapter : FavoriteAdapter

    lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DatabaseHelper(this)
        initview()
    }

    private fun initview() {

        list = db.FavoriteDisplayRecord()

        adapter =
            FavoriteAdapter { Shayari_id,fav ->
                db.UpdeteRecord(Shayari_id, fav)
            }

        var manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rcvfavorite.layoutManager = manager
        binding.rcvfavorite.adapter = adapter
        binding.imgBack.setOnClickListener{
            onBackPressed()
        }

        adapter.updateList(list)

        }
}