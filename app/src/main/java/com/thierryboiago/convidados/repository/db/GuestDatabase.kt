package com.thierryboiago.convidados.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thierryboiago.convidados.repository.constants.DataBaseConstants
import com.thierryboiago.convidados.repository.dao.GuestDAO
import com.thierryboiago.convidados.repository.model.GuestModel


@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase() : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO
    companion object{
        private lateinit var INSTANCE: GuestDatabase
        fun getDatabase(context: Context): GuestDatabase{
            if(!::INSTANCE.isInitialized){
                synchronized(GuestDatabase::class){
                    INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "guestdb")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }

            }

            return INSTANCE


        }
        private val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM Guest")
            }

        }
    }


/*class GuestDatabase(
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

    }*/
}