package com.haretskiy.pavel.calculatofortests.views

interface CalculatorView {

    fun setVisibilityOfHistory()

    fun printOnCalculatorDisplay(text: String)

    fun setHistoryContent(history: Array<String>)
}