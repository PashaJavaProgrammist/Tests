package com.haretskiy.pavel.calculatofortests

import com.haretskiy.pavel.calculatofortests.calculator.Calculator
import com.haretskiy.pavel.calculatofortests.calculator.CalculatorImpl
import com.haretskiy.pavel.calculatofortests.presenters.CalculatorPresenterImpl
import com.haretskiy.pavel.calculatofortests.store.Store
import com.haretskiy.pavel.calculatofortests.store.StoreImpl
import com.haretskiy.pavel.calculatofortests.views.CalculatorView
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

    @Mock
    private lateinit var mockedCalculator: Calculator
    @Mock
    private lateinit var mockedStore: Store
    @Mock
    private lateinit var mockedView: CalculatorView

    //  It works but we need to checked attaching view
    //    @InjectMocks
    private lateinit var calculatorPresenter: CalculatorPresenterImpl

    @Before
    fun setUp() {
        mockedCalculator = mock(CalculatorImpl::class.java)
        mockedStore = mock(StoreImpl::class.java)
        mockedView = mock(CalculatorView::class.java)

//         it doesn't need
//        MockitoAnnotations.initMocks(this)

        calculatorPresenter = CalculatorPresenterImpl(mockedCalculator, mockedStore)

    }

    @Test
    fun viewAttachDetachTest() {
        calculatorPresenter.view = null
        calculatorPresenter.attachView(mockedView)
        assertNotNull(calculatorPresenter.view)
        calculatorPresenter.detachView()
        assertEquals(null, calculatorPresenter.view)
    }

    @Test
    fun savingInStoreTest() {
        calculatorPresenter.clickNumber(1)
        calculatorPresenter.clickOperation(CODE_ADD)
        calculatorPresenter.clickNumber(1)
        calculatorPresenter.clickResult()

        verify(mockedStore).saveOperationInHistory(ArgumentMatchers.anyString())
    }

    @Test
    fun getFromStoreTest() {
        calculatorPresenter.clickHistory()
        verify(mockedStore).getAllOperationList()
    }

}