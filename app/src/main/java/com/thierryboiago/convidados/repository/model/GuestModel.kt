package com.thierryboiago.convidados.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class GuestModel {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var presence: Boolean = false
}