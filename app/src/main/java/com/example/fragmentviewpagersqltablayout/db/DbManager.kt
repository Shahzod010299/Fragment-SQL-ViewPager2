package com.example.fragmentviewpagersqltablayout.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.service.autofill.UserData

class DbManager(context: Context) {
    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = dbHelper.writableDatabase
    }

    // db ga yozish
    fun insertDB(user_name: String, user_password: String){
        val values = ContentValues().apply {
            put(DbNameCalss.COLUMN_USER_NAME, user_name)
            put(DbNameCalss.COLUMN_USER_PASSWORD, user_password)
        }

        db?.insert(DbNameCalss.TABLE_NAME, null, values)
    }

    // db dab o'qib olish
    @SuppressLint("Range")
    fun readData(): ArrayList<com.example.fragmentviewpagersqltablayout.models.UserData>{
        val data = ArrayList<com.example.fragmentviewpagersqltablayout.models.UserData>()

        val cursor = db?.query(
            DbNameCalss.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (this?.moveToNext()!!) {
                if (cursor != null)
                data.add(
                    com.example.fragmentviewpagersqltablayout.models.UserData(
                        cursor.getInt(cursor.getColumnIndex(DbNameCalss.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DbNameCalss.COLUMN_USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(DbNameCalss.COLUMN_USER_PASSWORD))
                    )
                )
            }
        }
        cursor?.close()
        return data
    }

    // delete id boyicha
    fun deletID(id: Int): Int? {
        return db?.delete(DbNameCalss.TABLE_NAME,"user_id = ?", arrayOf(id.toString()))
    }

    // db dan o'zgartirish
    fun updateDB(
        user_id: Int,
        user_name: String,
        user_password: String
    ){
        val values = ContentValues().apply {
            put(DbNameCalss.COLUMN_USER_NAME, user_name)
            put(DbNameCalss.COLUMN_USER_PASSWORD, user_password)
        }

        db?.update(DbNameCalss.TABLE_NAME,values,"user_id = ?", arrayOf(user_id.toString()))
    }

    fun closeDB(){
        dbHelper.close()
    }
}