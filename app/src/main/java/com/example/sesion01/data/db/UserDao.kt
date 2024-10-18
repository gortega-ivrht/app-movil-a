package com.example.sesion01.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.sesion01.data.model.User

class UserDao (context: Context){

    private val dbHelper = UserDatabaseHelper(context)

    fun insertUser(user: User): Long{
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name",user.name)
        }

        return db.insert("users",null, values).also {
            db.close()
        }

    }

    fun getAllUsers() : List<User>{
        val db = dbHelper.readableDatabase
        val cursor : Cursor = db.query("users",null,null,null,null,null,null)
        val users = mutableListOf<User>()

        if (cursor.moveToFirst()){

            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                users.add(User(id,name))
            } while (cursor.moveToNext())

        }

        cursor.close()
        db.close()

        return users
    }

    // sesion6 cursores
    fun getUsersFilter(nameFilter:String):List<User>{
        val db = dbHelper.readableDatabase
        // select id,name from users
        //where name LIKE '%g%'
        //order by id DESC

        //gean,igean,maria, mafer, pedro
        val projection =  arrayOf("id","name","email")
        val selection = "name LIKE ?"
        val selectionArgs = arrayOf("$nameFilter%")
        val orderBy = "id DESC"

        val users = mutableListOf<User>()

        val cursor : Cursor = db.query("users",
                                        projection,
                                        selection,
                                        selectionArgs,
                                        null,
                                        null,
                                        orderBy
                                        )

        if (cursor.moveToFirst()){
            do {
              //lógica
                val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))

                //procesar los datos obtenidos
                users.add(User(id,name,email))
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return users
    }

    fun updateUser(id:Long,newName:String): Int{

        val db = dbHelper.readableDatabase
        val values = ContentValues().apply {
            put("name",newName)
        }
        val selection = "id = ?"
        val selectionArgs = arrayOf(id.toString())

        return db.update("users", values,selection, selectionArgs)
    }

    fun deleteUser(id: Long): Int{
        val db = dbHelper.readableDatabase
        val selection = "id = ?"
        val selectionArgs = arrayOf(id.toString())
        return db.delete("users",selection,selectionArgs)
    }

}