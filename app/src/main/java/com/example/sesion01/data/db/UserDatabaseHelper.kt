package com.example.sesion01.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class UserDatabaseHelper (context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "user_database.bd"
        private const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)*/
        Log.d("oldVersion",oldVersion.toString())
        if (oldVersion < 5){
            Log.d("onUpgrade","Agregando la columna email en la versión 2")
            db.execSQL("ALTER TABLE users ADD COLUMN email TEXT DEFAULT 'sin email'")
        }
    }

}