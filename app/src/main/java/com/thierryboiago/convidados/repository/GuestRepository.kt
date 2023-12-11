package com.thierryboiago.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.thierryboiago.convidados.repository.constants.DataBaseConstants
import com.thierryboiago.convidados.repository.db.GuestDatabase
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDatabase = GuestDatabase(context)

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDatabase.writableDatabase

            val values = ContentValues()

            val presence = if (guest.presence) 1 else 0

            values.put(DataBaseConstants.GUEST.COLUNMS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUNMS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDatabase.writableDatabase
            val values = ContentValues()

            val presence = if (guest.presence) 1 else 0

            values.put(DataBaseConstants.GUEST.COLUNMS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUNMS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.COLUNMS.ID + " = ?"
            val args = arrayOf(guest.id.toString())


            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDatabase.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUNMS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }


    companion object {
        private lateinit var repository: GuestRepository


        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }
}