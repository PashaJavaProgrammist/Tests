package com.haretskiy.pavel.calculatofortests.store

interface Store {
    fun saveOperationInHistory(operation: String)
    fun getAllOperationList(): List<String>
}