package com.example.fragmentviewpagersqltablayout.db

object DbNameCalss {
    const val TABLE_NAME = "my_table_user"
    const val COLUMN_ID = "user_id"
    const val COLUMN_USER_NAME = "user_name"
    const val COLUMN_USER_PASSWORD = "user_password"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "my_db.db"


    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_USER_NAME TEXT," +
                "$COLUMN_USER_PASSWORD TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

}