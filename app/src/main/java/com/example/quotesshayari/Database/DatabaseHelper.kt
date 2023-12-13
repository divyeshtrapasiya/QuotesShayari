package com.example.externaldatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import com.example.quotesshayari.Model.CatagoryModel
import com.example.quotesshayari.Model.FavotiteModel
import com.example.quotesshayari.Model.QuotesModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val mDataBase: SQLiteDatabase? = null
    private var mNeedUpdate = false
    private val mContext: Context

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    //    TODO copy file
    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = mContext.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) mNeedUpdate = true
    }

    //    TODO update database
    @Throws(IOException::class)
    fun updateDataBase(sql: String) {
        if (mNeedUpdate) {
            val dbFile = File(DB_PATH + DB_NAME)
            if (dbFile.exists()) dbFile.delete()
            copyDataBase()
            mNeedUpdate = false
        }
    }

    @Synchronized
    override fun close() {
        mDataBase?.close()
        super.close()


    }

    fun displayrecord(): ArrayList<CatagoryModel> {

        val list = ArrayList<CatagoryModel>()
        list.clear()
        val db = readableDatabase
        val sql = "select * from catagoryTb "
        val cursor: Cursor = db.rawQuery(sql, null)


        if (cursor.moveToFirst()) {
            do {

                val id = cursor.getInt(0)
                val name = cursor.getString(1)

                Log.e("TAG", "displayrecord: " + id + " " + name)

                val amodal = CatagoryModel(id, name)
                list.add(amodal)

            } while (cursor.moveToNext())
        }
        return list
    }

    fun Quotesdisplay(id: Int): ArrayList<QuotesModel> {

        val list = ArrayList<QuotesModel>()
        list.clear()
        val db = readableDatabase
        val sql = "select * from QuotesTb where catagoryid = $id"
        val cursor: Cursor = db.rawQuery(sql, null)


        if (cursor.moveToFirst()) {
            do {

                val id = cursor.getInt(0)
                val quotes = cursor.getString(1)
                val status = cursor.getInt(2)

                Log.e("TAG", "displayrecord: " + id + " " + quotes + " " + status)

                val amodal = QuotesModel(id, quotes, status)
                list.add(amodal)

            } while (cursor.moveToNext())
        }
        return list
    }

    fun UpdeteRecord(id: Int,status : Int)
    {

        var db = writableDatabase

        var c = ContentValues()

        c.put("status",status)

        Log.e(TAG, "UpdeteRecord: "+status+"   "+id)

        var sql = "update QuotesTb set Status = $status where id =$id"

        Log.e(TAG, "UpdeteRecord: "+sql)

        db.execSQL(sql)


    }

    fun editrecord(id: Int,quotes : String)
    {
        var db = writableDatabase
        var c=ContentValues()

        c.put("quotes",quotes)

        var update = "update QuotesTb set name = '$quotes' where id ='$id'"
        Log.e(TAG, "editrecord: "+quotes+"  "+id )

        db.execSQL(update)
    }

    fun FavoriteDisplayRecord() : ArrayList<FavotiteModel>{
        var DisplayList = ArrayList<FavotiteModel>()

        val dbF = readableDatabase
        val SqlF = "Select * from quotesTb where status = 1"
        val c = dbF.rawQuery(SqlF,null)
        if (  c.moveToFirst()){

            do {
                var Shayari_id = c.getInt(0)
                var Shayari = c.getString(1)
                var fav = c.getInt(2)

                Log.e("TAG", "FavoriteDisplayRecord: $Shayari_id $Shayari" )
                var shayarimodal = FavotiteModel(Shayari_id,Shayari,fav)

                DisplayList.add(shayarimodal)
            }while (c.moveToNext())
        }
        return DisplayList
    }



    companion object {
        private const val TAG = "MyDatabase"
        private const val DB_NAME = "MyDatabase.db"
        private var DB_PATH = ""
        private const val DB_VERSION = 1
    }

    init {
        if (Build.VERSION.SDK_INT >= 17) DB_PATH =
            context.applicationInfo.dataDir + "/databases/" else DB_PATH =
            "/data/data/" + context.packageName + "/databases/"
        mContext = context
        copyDataBase()
        this.readableDatabase
    }
}