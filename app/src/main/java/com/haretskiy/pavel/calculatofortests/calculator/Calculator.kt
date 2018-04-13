package com.haretskiy.pavel.calculatofortests.calculator

interface Calculator {

    fun calculate(a: Int?, b: Int?, operation: (a: Int?, b: Int?) -> Int? ): Int?
}