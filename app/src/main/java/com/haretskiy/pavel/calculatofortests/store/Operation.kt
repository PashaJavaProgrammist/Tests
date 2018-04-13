package com.haretskiy.pavel.calculatofortests.store

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "operations")
data class Operation(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var operation: String = ""
)