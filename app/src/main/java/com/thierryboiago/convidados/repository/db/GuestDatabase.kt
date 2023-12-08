package com.thierryboiago.convidados.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.thierryboiago.convidados.repository.constants.DataBaseConstants

class GuestDatabase(
    context: Context
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + DataBaseConstants.GUEST.TABLE_NAME + "(" +
                    DataBaseConstants.GUEST.COLUNMS.ID + " integer primary key autoincrement," +
                    DataBaseConstants.GUEST.COLUNMS.NAME + " text," +
                    DataBaseConstants.GUEST.COLUNMS.PRESENCE + "  integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}