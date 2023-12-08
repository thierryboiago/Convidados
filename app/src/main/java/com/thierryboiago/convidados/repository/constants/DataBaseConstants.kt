package com.thierryboiago.convidados.repository.constants

class DataBaseConstants private constructor(){
    object GUEST {
        const val TABLE_NAME = "GUEST"

        object COLUNMS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}