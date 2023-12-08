package com.thierryboiago.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.thierryboiago.convidados.repository.db.GuestDatabase
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDatabase = GuestDatabase(context)

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDatabase.writableDatabase

            val values = ContentValues()

            val presence = if (guest.presence) 1 else 0

            values.put("name", guest.name)
            values.put("presence", presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update() {

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