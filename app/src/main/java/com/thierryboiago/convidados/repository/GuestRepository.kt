package com.thierryboiago.convidados.repository

import android.content.Context
import com.thierryboiago.convidados.repository.db.GuestDatabase
import com.thierryboiago.convidados.repository.model.GuestModel

class GuestRepository constructor(context: Context) {

    private val guestDatabase = GuestDatabase.getDatabase(context).guestDAO()

    fun insert(guest: GuestModel): Boolean = guestDatabase.insert(guest) > 0

    fun update(guest: GuestModel): Boolean = guestDatabase.update(guest) > 0

    fun delete(id: Int) {
        val guest = getGuest(id)
        guestDatabase.delete(guest)
    }

    fun getAll(): List<GuestModel> = guestDatabase.getAll()


    fun getWithPresence(presence: Int): List<GuestModel> = guestDatabase.getWithPresence(presence)

    fun getGuest(id: Int): GuestModel = guestDatabase.get(id)


}