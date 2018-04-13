package com.haretskiy.pavel.calculatofortests.store

class StoreImpl(val storeDao: StoreDao) : Store {
    override fun saveOperationInHistory(operation: String) {
        storeDao.insert(Operation(0, operation))
    }

    override fun getAllOperationList() = storeDao.all.map { it.operation }
}