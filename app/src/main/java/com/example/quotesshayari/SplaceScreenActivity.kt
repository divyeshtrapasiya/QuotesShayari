package com.example.quotesshayari

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesshayari.databinding.ActivitySplacescreenBinding

class SplaceScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplacescreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplacescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()

    }

    private fun initview() {
        Handler().postDelayed({
            val mainIntent = Intent(this, DaseBoardActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)

    }
}