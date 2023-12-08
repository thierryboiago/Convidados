package com.thierryboiago.convidados.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GuestDatabase(
    context: Context
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table Guest(" +
                "id integer primary key autoincrement," +
                "name text," +
                "presence integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}