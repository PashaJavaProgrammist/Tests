package com.haretskiy.pavel.calculatofortests.calculator

class CalculatorImpl : Calculator {

    override fun calculate(a: Int?, b: Int?, operation: (a: Int?, b: Int?) -> Int?) = operation(a, b)

}