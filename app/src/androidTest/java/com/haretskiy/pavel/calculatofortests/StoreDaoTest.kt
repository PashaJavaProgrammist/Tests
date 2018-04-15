package com.haretskiy.pavel.calculatofortests

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.haretskiy.pavel.calculatofortests.store.Database
import com.haretskiy.pavel.calculatofortests.store.Operation
import com.haretskiy.pavel.calculatofortests.store.StoreDao
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class StoreDaoTest {


    private lateinit var db: Database
    private lateinit var storeDao: StoreDao

    @Before
    @Throws(Exception::class)
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                Database::class.java)
                .build()
        storeDao = db.storeDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertOperationThenReadTheSameOneTest() {

        val operationToInsert = Operation(0, "Test")

        storeDao.insert(operationToInsert)
        val operationsListPostInsert = storeDao.all

        assertEquals(1, operationsListPostInsert.size)
        assertTrue(operationsListPostInsert[0].operation == "Test")
    }

}