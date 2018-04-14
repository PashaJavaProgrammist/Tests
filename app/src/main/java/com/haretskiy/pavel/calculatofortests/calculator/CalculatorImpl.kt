package com.haretskiy.pavel.calculatofortests.calculator

class CalculatorImpl : Calculator {
    override fun addition(a: Float, b: Float) = a + b

    override fun subtraction(a: Float, b: Float) = a - b

    override fun multiplication(a: Float, b: Float) = a * b

    override fun divide(a: Float, b: Float): Float {
        require(b.isFinite())
        require(b != 0F)
        return a / b
    }

    override fun calculate(a: Float, b: Float, operation: (a: Float, b: Float) -> Float) = operation(a, b)

}