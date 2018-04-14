package com.haretskiy.pavel.calculatofortests

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.haretskiy.pavel.calculatofortests.calculator.Calculator
import com.haretskiy.pavel.calculatofortests.calculator.CalculatorImpl
import com.haretskiy.pavel.calculatofortests.store.Database
import com.haretskiy.pavel.calculatofortests.store.Store
import com.haretskiy.pavel.calculatofortests.store.StoreImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var firstNumStr = EMPTY
    private var secondNumStr = EMPTY
    private var firstNum: Float = 0f
    private var secondNum: Float = 0f

    private var resultNumStr = EMPTY
    private var resultNum = 0f

    private var codeOperation: Int = EMPTY_CODE_OPERATION

    private var isHistoryVisible = false

    private val operators = arrayOf(PLUS, MINUS, MULTIPLE, DIVIDE)
    private var operatationsHistory = emptyArray<String>()

    private var calculatedExpression = EMPTY

    private val calculator: Calculator by lazy {
        CalculatorImpl()
    }

    private val store: Store by lazy {
        StoreImpl(Room.databaseBuilder(applicationContext, Database::class.java, DB_NAME).allowMainThreadQueries().build().storeDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
        initListView()
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

            btAdd -> clickOperation(CODE_ADD)
            btDiv -> clickOperation(CODE_DIVIDE)
            btMult -> clickOperation(CODE_MULTIPLE)
            btSub -> clickOperation(CODE_SUB)

            btIs -> clickResult()
            btClearLast -> clickClearLast()
            btClearAll -> clickClearAll()
            btHistory -> clickHistory()

        }
    }

    private fun initListView() {
        tvHistory.setOnItemClickListener { _, _, position, _ ->
            clickHistory()
            printInCalculatorWindow(operatationsHistory[position])
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

    private fun makeButtonsVisible() {
        bt0.visibility = View.VISIBLE
        bt1.visibility = View.VISIBLE
        bt2.visibility = View.VISIBLE
        bt3.visibility = View.VISIBLE
        bt4.visibility = View.VISIBLE
        bt5.visibility = View.VISIBLE
        bt6.visibility = View.VISIBLE
        bt7.visibility = View.VISIBLE
        bt8.visibility = View.VISIBLE
        bt9.visibility = View.VISIBLE

        btAdd.visibility = View.VISIBLE
        btDiv.visibility = View.VISIBLE
        btMult.visibility = View.VISIBLE
        btSub.visibility = View.VISIBLE

        btIs.visibility = View.VISIBLE
        btClearAll.visibility = View.VISIBLE
        btClearLast.visibility = View.VISIBLE

        calculatorDisplay.visibility = View.VISIBLE
    }

    private fun makeButtonsInvisible() {
        bt0.visibility = View.GONE
        bt1.visibility = View.GONE
        bt2.visibility = View.GONE
        bt3.visibility = View.GONE
        bt4.visibility = View.GONE
        bt5.visibility = View.GONE
        bt6.visibility = View.GONE
        bt7.visibility = View.GONE
        bt8.visibility = View.GONE
        bt9.visibility = View.GONE

        btAdd.visibility = View.GONE
        btDiv.visibility = View.GONE
        btMult.visibility = View.GONE
        btSub.visibility = View.GONE

        btIs.visibility = View.GONE
        btClearAll.visibility = View.GONE
        btClearLast.visibility = View.GONE
        calculatorDisplay.visibility = View.GONE

    }

    private fun clickNumber(num: Int) {
        if (resultNumStr.isEmpty()) {
            calculatedExpression += num
            if (codeOperation == EMPTY_CODE_OPERATION) {
                firstNumStr = calculatedExpression
                printInCalculatorWindow(firstNumStr)
            } else {
                secondNumStr = calculatedExpression
                printInCalculatorWindow("$firstNumStr ${operators[codeOperation]} $secondNumStr")
            }
        }
    }

    private fun clickOperation(code: Int) {
        if (resultNumStr.isEmpty() && secondNumStr.isEmpty()) {
            if (!firstNumStr.isEmpty()) {
                codeOperation = code
                calculatedExpression = EMPTY
                printInCalculatorWindow("$firstNumStr ${operators[codeOperation]}")
            }
        }
    }

    private fun clickResult() {
        var res = EMPTY
        if (codeOperation != EMPTY_CODE_OPERATION && !secondNumStr.isEmpty()) {
            firstNum = firstNumStr.toFloat()
            secondNum = secondNumStr.toFloat()
            when (codeOperation) {
                CODE_ADD -> resultNum = calculator.addition(firstNum, secondNum)
                CODE_SUB -> resultNum = calculator.subtraction(firstNum, secondNum)
                CODE_MULTIPLE -> resultNum = calculator.multiplication(firstNum, secondNum)
                CODE_DIVIDE -> {
                    if (secondNum == 0f) res = getString(R.string.byzero)
                    else resultNum = calculator.divide(firstNum, secondNum)
                }
            }
            if (secondNum != 0f) res = "$firstNumStr ${operators[codeOperation]} $secondNumStr = $resultNum"
            printInCalculatorWindow(res)
            store.saveOperationInHistory(res)
        }
    }

    private fun clickClearAll() {
        calculatedExpression = EMPTY
        firstNumStr = EMPTY
        secondNumStr = EMPTY
        resultNumStr = EMPTY
        codeOperation = EMPTY_CODE_OPERATION
        printInCalculatorWindow(EMPTY)
    }

    private fun clickClearLast() {
        if (resultNumStr.isEmpty()) {
            when (true) {
                !secondNumStr.isEmpty() -> {
                    calculatedExpression = calculatedExpression.substring(0, calculatedExpression.length - 1)
                    if (calculatedExpression.isEmpty()) {
                        secondNumStr = EMPTY
                        printInCalculatorWindow("$firstNumStr${operators[codeOperation]}")
                    } else {
                        secondNumStr = calculatedExpression
                        printInCalculatorWindow("$firstNumStr${operators[codeOperation]}$secondNumStr")
                    }
                }
                codeOperation != EMPTY_CODE_OPERATION -> {
                    codeOperation = EMPTY_CODE_OPERATION
                    calculatedExpression = firstNumStr
                    printInCalculatorWindow(firstNumStr)
                }
                !firstNumStr.isEmpty() -> {
                    calculatedExpression = calculatedExpression.substring(0, calculatedExpression.length - 1)
                    if (calculatedExpression.isEmpty()) {
                        firstNumStr = EMPTY
                        printInCalculatorWindow(EMPTY)
                    } else {
                        firstNumStr = calculatedExpression
                        printInCalculatorWindow(firstNumStr)
                    }
                }
            }
        }
    }

    private fun setVisibilityOfHistory() {
        if (!isHistoryVisible) {
            frameHistory.visibility = View.VISIBLE
            makeButtonsInvisible()
            isHistoryVisible = true
        } else {
            frameHistory.visibility = View.GONE
            makeButtonsVisible()
            isHistoryVisible = false
        }
    }

    private fun clickHistory() {
        setVisibilityOfHistory()
        operatationsHistory = store.getAllOperationList().toTypedArray()
        operatationsHistory.reverse()
        tvHistory.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, operatationsHistory)
    }

    private fun printInCalculatorWindow(text: String) {
        calculatorDisplay.text = text
    }
}
