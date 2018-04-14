package com.haretskiy.pavel.calculatofortests

import com.haretskiy.pavel.calculatofortests.calculator.Calculator
import com.haretskiy.pavel.calculatofortests.calculator.CalculatorImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = CalculatorImpl()
    }

    @Test
    fun testAddition() {
        val res = calculator.addition(2f, 2f)
        Assert.assertEquals(4f, res)
    }

    @Test
    fun testSubtraction() {
        val res = calculator.subtraction(2f, 2f)
        Assert.assertEquals(0f, res)
    }

    @Test
    fun testMultiplication() {
        val res = calculator.multiplication(3f, 2f)
        Assert.assertEquals(6f, res)

    }

    @Test
    fun testDivide() {
        val res = calculator.divide(5f, 3f)
        Assert.assertEquals(1.666f, res, 0.001f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDivideByZero() {
        calculator.divide(5f, 0f)
    }
}