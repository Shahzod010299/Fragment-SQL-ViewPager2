package com.example.fragmentviewpagersqltablayout.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context): SQLiteOpenHelper(
    context, DbNameCalss.DATABASE_NAME,null,DbNameCalss.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbNameCalss.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DbNameCalss.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}