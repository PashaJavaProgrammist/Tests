package com.haretskiy.pavel.calculatofortests.presenters

import com.haretskiy.pavel.calculatofortests.*
import com.haretskiy.pavel.calculatofortests.calculator.Calculator
import com.haretskiy.pavel.calculatofortests.store.Store
import com.haretskiy.pavel.calculatofortests.views.CalculatorView

class CalculatorPresenterImpl(private val calculator: Calculator, private val store: Store) : CalculatorPresenter {

    private var firstNumStr = EMPTY
    private var secondNumStr = EMPTY
    private var firstNum: Float = 0f
    private var secondNum: Float = 0f

    private var resultNumStr = EMPTY
    private var resultNum = 0f

    private var codeOperation: Int = EMPTY_CODE_OPERATION

    private var calculatedExpression = EMPTY
    private var operatationsHistory = emptyArray<String>()
    private val operators = arrayOf(PLUS, MINUS, MULTIPLE, DIVIDE)

    var view: CalculatorView? = null

    override fun attachView(view: CalculatorView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }


    override fun clickNumber(num: Int) {
        if (resultNumStr.isEmpty()) {
            calculatedExpression += num
            if (codeOperation == EMPTY_CODE_OPERATION) {
                firstNumStr = calculatedExpression
                view?.printOnCalculatorDisplay(firstNumStr)
            } else {
                secondNumStr = calculatedExpression
                view?.printOnCalculatorDisplay("$firstNumStr ${operators[codeOperation]} $secondNumStr")
            }
        }
    }

    override fun clickOperation(code: Int) {
        if (resultNumStr.isEmpty() && secondNumStr.isEmpty()) {
            if (!firstNumStr.isEmpty()) {
                codeOperation = code
                calculatedExpression = EMPTY
                view?.printOnCalculatorDisplay("$firstNumStr ${operators[codeOperation]}")
            }
        }
    }

    override fun clickResult() {
        var res = EMPTY
        if (codeOperation != EMPTY_CODE_OPERATION && !secondNumStr.isEmpty()) {
            firstNum = firstNumStr.toFloat()
            secondNum = secondNumStr.toFloat()
            when (codeOperation) {
                CODE_ADD -> resultNum = calculator.addition(firstNum, secondNum)
                CODE_SUB -> resultNum = calculator.subtraction(firstNum, secondNum)
                CODE_MULTIPLE -> resultNum = calculator.multiplication(firstNum, secondNum)
                CODE_DIVIDE -> {
                    if (secondNum == 0f) res = BY_ZERO
                    else resultNum = calculator.divide(firstNum, secondNum)
                }
            }
            if (secondNum != 0f) res = "$firstNumStr ${operators[codeOperation]} $secondNumStr = $resultNum"
            view?.printOnCalculatorDisplay(res)
            store.saveOperationInHistory(res)
            resultNumStr = resultNum.toString()
        }
    }

    override fun clickClearAll() {
        calculatedExpression = EMPTY
        firstNumStr = EMPTY
        secondNumStr = EMPTY
        resultNumStr = EMPTY
        codeOperation = EMPTY_CODE_OPERATION
        view?.printOnCalculatorDisplay(EMPTY)
    }

    override fun clickClearLast() {
        if (resultNumStr.isEmpty()) {
            when (true) {
                !secondNumStr.isEmpty() -> {
                    calculatedExpression = calculatedExpression.substring(0, calculatedExpression.length - 1)
                    if (calculatedExpression.isEmpty()) {
                        secondNumStr = EMPTY
                        view?.printOnCalculatorDisplay("$firstNumStr${operators[codeOperation]}")
                    } else {
                        secondNumStr = calculatedExpression
                        view?.printOnCalculatorDisplay("$firstNumStr${operators[codeOperation]}$secondNumStr")
                    }
                }
                codeOperation != EMPTY_CODE_OPERATION -> {
                    codeOperation = EMPTY_CODE_OPERATION
                    calculatedExpression = firstNumStr
                    view?.printOnCalculatorDisplay(firstNumStr)
                }
                !firstNumStr.isEmpty() -> {
                    calculatedExpression = calculatedExpression.substring(0, calculatedExpression.length - 1)
                    if (calculatedExpression.isEmpty()) {
                        firstNumStr = EMPTY
                        view?.printOnCalculatorDisplay(EMPTY)
                    } else {
                        firstNumStr = calculatedExpression
                        view?.printOnCalculatorDisplay(firstNumStr)
                    }
                }
            }
        }
    }

    override fun clickHistory() {
        view?.setVisibilityOfHistory()
        operatationsHistory = store.getAllOperationList().toTypedArray()
        operatationsHistory.reverse()
        view?.setHistoryContent(operatationsHistory)
    }

    override fun clickHistoryItem(position: Int) {
        view?.printOnCalculatorDisplay(operatationsHistory[position])
    }

}