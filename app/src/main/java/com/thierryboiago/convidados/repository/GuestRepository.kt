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

    fun getAll(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDatabase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUNMS.ID,
                DataBaseConstants.GUEST.COLUNMS.NAME,
                DataBaseConstants.GUEST.COLUNMS.PRESENCE,
            )
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUNMS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUNMS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUNMS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        } catch (exception: Exception) {
            return list
        }

        return list


    }


    fun getWithPresence(presence: Int): List<GuestModel> {

        val list = mutableListOf<GuestModel>()
        try {
            val db = guestDatabase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUNMS.ID,
                DataBaseConstants.GUEST.COLUNMS.NAME,
                DataBaseConstants.GUEST.COLUNMS.PRESENCE,
            )

            val selection = DataBaseConstants.GUEST.COLUNMS.PRESENCE + " = ?"
            val args = arrayOf(presence.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUNMS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUNMS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUNMS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            cursor.close()
        } catch (exception: Exception) {
            return list
        }

        return list


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