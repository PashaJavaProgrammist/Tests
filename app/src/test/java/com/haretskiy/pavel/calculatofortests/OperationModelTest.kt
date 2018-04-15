package com.haretskiy.pavel.calculatofortests

import com.haretskiy.pavel.calculatofortests.store.Operation
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OperationModelTest {

    var operation: Operation? = null

    @Test
    fun testCreatingOperation() {
        operation = Operation(PALACEBO_ID, TEST)
        Assert.assertNotNull(operation)
        Assert.assertEquals(PALACEBO_ID, operation?.id)
        Assert.assertEquals(TEST, operation?.operation)
    }
}