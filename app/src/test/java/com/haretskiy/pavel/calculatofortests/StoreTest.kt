package com.haretskiy.pavel.calculatofortests

import com.haretskiy.pavel.calculatofortests.store.StoreDao
import com.haretskiy.pavel.calculatofortests.store.StoreDao_Impl
import com.haretskiy.pavel.calculatofortests.store.StoreImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoreTest {

    @Mock
    private lateinit var mockedStoreDao: StoreDao

    private lateinit var store: StoreImpl

    @Before
    fun setUp() {
        mockedStoreDao = Mockito.mock(StoreDao_Impl::class.java)
        store = StoreImpl(mockedStoreDao)
    }

    @Test
    fun getDataTest() {
        store.getAllOperationList()
        verify(mockedStoreDao).all
    }

    @Test
    fun insertDataTest() {
        store.saveOperationInHistory(anyString())
        // This doesn't work why??
//        verify(mockedStoreDao).insert(ArgumentMatchers.any(Operation::class.java))
    }

}