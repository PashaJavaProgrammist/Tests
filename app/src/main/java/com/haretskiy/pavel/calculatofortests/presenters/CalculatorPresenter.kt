package com.haretskiy.pavel.calculatofortests.presenters

import com.haretskiy.pavel.calculatofortests.views.CalculatorView

interface CalculatorPresenter : Presenter<CalculatorView> {

    fun clickNumber(num: Int)

    fun clickOperation(code: Int)

    fun clickResult()

    fun clickClearAll()

    fun clickClearLast()

    fun clickHistory()

    fun clickHistoryItem(position: Int)
}