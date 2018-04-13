package com.haretskiy.pavel.calculatofortests

interface Calculator {

    fun calculate(a: Int?, b: Int?, operation: (a: Int?, b: Int?) -> Int? ): Int?
}