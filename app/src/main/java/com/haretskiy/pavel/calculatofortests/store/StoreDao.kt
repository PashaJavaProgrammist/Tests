package com.haretskiy.pavel.calculatofortests.store

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface StoreDao {

    @get:Query("SELECT * FROM operations")
    val all: List<Operation>

    @Insert
    fun insert(operation: Operation)

}