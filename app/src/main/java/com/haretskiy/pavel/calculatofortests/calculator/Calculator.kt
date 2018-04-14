package com.haretskiy.pavel.calculatofortests.calculator

interface Calculator {

    fun calculate(a: Float, b: Float, operation: (a: Float, b: Float) -> Float): Float

    fun addition(a: Float, b: Float): Float

    fun subtraction(a: Float, b: Float): Float

    fun multiplication(a: Float, b: Float): Float

    fun divide(a: Float, b: Float): Float

}