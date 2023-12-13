package com.example.quotesshayari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.externaldatabase.DatabaseHelper
import com.example.quotesshayari.Adapter.CatagoryAdapter
import com.example.quotesshayari.Model.CatagoryModel
import com.example.quotesshayari.databinding.ActivityDaseBoardBinding

class DaseBoardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDaseBoardBinding
    lateinit var databaseHelper: DatabaseHelper
    var catagory = ArrayList<CatagoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaseBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()

    }

    private fun initview() {

        databaseHelper = DatabaseHelper(this)
        catagory = databaseHelper.displayrecord()

        binding.imgbtnlike.setOnClickListener {
            var i = Intent(this,FavoriteActivity::class.java)
            startActivity(i)
        }
       var myAdapter = CatagoryAdapter(this,catagory, onitemclick =  {id ->

           var i = Intent(this,QuotesActivity::class.java)
           i.putExtra("id",id)
           startActivity(i)

       })

        var manager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recycleview.layoutManager=manager

        binding.recycleview.adapter=myAdapter


    }
}