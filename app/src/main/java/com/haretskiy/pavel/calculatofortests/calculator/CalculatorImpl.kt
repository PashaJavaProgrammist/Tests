package com.haretskiy.pavel.calculatofortests.calculator

class CalculatorImpl : Calculator {

    override fun calculate(a: Float?, b: Float?, operation: (a: Float?, b: Float?) -> Float?) = operation(a, b)

}