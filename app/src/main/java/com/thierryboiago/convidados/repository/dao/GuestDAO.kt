package com.thierryboiago.convidados.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.thierryboiago.convidados.repository.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guestModel: GuestModel): Long

    @Update
    fun update(guestModel: GuestModel): Int

    @Delete
    fun delete(guestModel: GuestModel)

    @Query("SELECT * FROM GUEST WHERE ID = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM GUEST")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM GUEST WHERE presence = :presence")
    fun getWithPresence(presence: Int): List<GuestModel>


}