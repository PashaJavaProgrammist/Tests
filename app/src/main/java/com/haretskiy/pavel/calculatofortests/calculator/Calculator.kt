package com.haretskiy.pavel.calculatofortests.calculator

interface Calculator {

    fun calculate(a: Float?, b: Float?, operation: (a: Float?, b: Float?) -> Float? ): Float?
}