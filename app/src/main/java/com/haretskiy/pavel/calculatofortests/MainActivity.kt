package com.haretskiy.pavel.calculatofortests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var firstNum: Int? = null
    private var secondNum: Int? = null
    private var codeOperation: Int? = null
    private var resultNum: Int? = null

    private val operators = arrayOf("+", "-", "*", "/")

    private var calculatedExpression = ""

    private val calculator: Calculator by lazy {
        CalculatorImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    override fun onClick(v: View?) {
        when (v) {
            bt0 -> clickNumber(0)
            bt1 -> clickNumber(1)
            bt2 -> clickNumber(2)
            bt3 -> clickNumber(3)
            bt4 -> clickNumber(4)
            bt5 -> clickNumber(5)
            bt6 -> clickNumber(6)
            bt7 -> clickNumber(7)
            bt8 -> clickNumber(8)
            bt9 -> clickNumber(9)

            btAdd -> clickoperation(CODE_ADD)
            btDiv -> clickoperation(CODE_DIV)
            btMult -> clickoperation(CODE_MULT)
            btSub -> clickoperation(CODE_SUB)

            btIs -> clickResult()
            btClearLast -> clickClearLast()
            btClearAll -> clickClearAll()
            btHistory -> clickHistory()

        }
    }

    private fun initListeners() {
        bt0.setOnClickListener(this)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
        bt8.setOnClickListener(this)
        bt9.setOnClickListener(this)

        btAdd.setOnClickListener(this)
        btDiv.setOnClickListener(this)
        btMult.setOnClickListener(this)
        btSub.setOnClickListener(this)

        btIs.setOnClickListener(this)
        btClearAll.setOnClickListener(this)
        btClearLast.setOnClickListener(this)
        btHistory.setOnClickListener(this)
    }

    private fun clickNumber(num: Int) {
        if (resultNum == null) {
            calculatedExpression += num
            if (codeOperation == null) {
                firstNum = calculatedExpression.toInt()
                printInCalculatorWindow(firstNum.toString())
            } else {
                secondNum = calculatedExpression.toInt()
                printInCalculatorWindow("$firstNum ${operators[codeOperation!!]} $secondNum")
            }
        }
    }

    private fun clickoperation(code: Int) {
        if (resultNum == null && secondNum == null) {
            if (firstNum != null) {
                codeOperation = code
                calculatedExpression = ""
                printInCalculatorWindow("$firstNum ${operators[codeOperation!!]}")
            }
        }
    }

    private fun clickResult() {
        if (codeOperation != null && secondNum != null) {
            when (codeOperation) {
                CODE_ADD -> resultNum = calculator.calculate(firstNum, secondNum, { a, b -> b?.let { a?.plus(it) } })
                CODE_SUB -> resultNum = calculator.calculate(firstNum, secondNum, { a, b -> b?.let { a?.minus(it) } })
                CODE_MULT -> resultNum = calculator.calculate(firstNum, secondNum, { a, b -> b?.let { a?.times(it) } })
                CODE_DIV -> resultNum = calculator.calculate(firstNum, secondNum, { a, b -> b?.let { a?.div(it) } })
            }
            printInCalculatorWindow("$firstNum ${operators[codeOperation!!]} $secondNum = $resultNum")
        }
    }

    private fun clickClearAll() {
        calculatedExpression = ""
        firstNum = null
        secondNum = null
        resultNum = null
        codeOperation = null
        printInCalculatorWindow("")
    }

    private fun clickClearLast() {
        if (resultNum == null) {
        }
    }

    private fun clickHistory() {
    }

    private fun printInCalculatorWindow(text: String) {
        textView.text = text
    }
}
