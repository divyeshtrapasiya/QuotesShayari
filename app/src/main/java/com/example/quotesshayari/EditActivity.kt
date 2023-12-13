package com.example.quotesshayari

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.externaldatabase.DatabaseHelper
import com.example.quotesshayari.databinding.ActivityEditBinding
import java.io.IOException

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    var id =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {

        var db = DatabaseHelper(this)

        if (intent != null)
        {
             id = intent.getIntExtra("id",0)
            var shayari = intent.getStringExtra("shayari")
            binding.edtquotes.setText(shayari)

        }

        binding.imgbtnsave.setOnClickListener{

            var quotes = binding.edtquotes.text.toString()

            db.editrecord(id,quotes)
            Log.e(TAG, "initview: "+id )

            Toast.makeText(this, "$quotes", Toast.LENGTH_SHORT).show()

            finish()

        }

        binding.imgbtngallary.setOnClickListener(View.OnClickListener {
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT

            // pass the constant to compare it
            // with the returned requestCode
            startActivityForResult(Intent.createChooser(i, "Select Picture"), 100)
        })

        binding.imgbtndownload.setOnClickListener {


            val z: View = binding.imgsetbacgruond
            z.isDrawingCacheEnabled = true
            val totalHeight: Int = z.getHeight()
            val totalWidth: Int = z.getWidth()
            z.layout(0, 0, totalWidth, totalHeight)
            z.buildDrawingCache(true)
            val bm: Bitmap = Bitmap.createBitmap(z.drawingCache)
            z.isDrawingCacheEnabled = false
            Toast.makeText(this, "Download Successfully", Toast.LENGTH_SHORT)
                .show()
            MediaStore.Images.Media.insertImage(contentResolver,bm,null,null)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedImageUri = data!!.data
            var selectedImageBitmap: Bitmap? = null
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                    this.contentResolver,
                    selectedImageUri
                )
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("TAG", "onActivityResult: " + e.message)
            }
            binding.imgsetbacgruond.setImageBitmap(selectedImageBitmap)
        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            val photo = data!!.extras!!["data"] as Bitmap?
        }
    }
}

