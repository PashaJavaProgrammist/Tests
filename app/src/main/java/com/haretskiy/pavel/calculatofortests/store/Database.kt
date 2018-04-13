package com.haretskiy.pavel.calculatofortests.store

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Operation::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun storeDao(): StoreDao
}