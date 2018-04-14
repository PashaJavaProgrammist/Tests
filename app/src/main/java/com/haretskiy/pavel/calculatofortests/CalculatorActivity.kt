package com.haretskiy.pavel.calculatofortests

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.haretskiy.pavel.calculatofortests.calculator.Calculator
import com.haretskiy.pavel.calculatofortests.calculator.CalculatorImpl
import com.haretskiy.pavel.calculatofortests.presenters.CalculatorPresenter
import com.haretskiy.pavel.calculatofortests.presenters.CalculatorPresenterImpl
import com.haretskiy.pavel.calculatofortests.store.Database
import com.haretskiy.pavel.calculatofortests.store.Store
import com.haretskiy.pavel.calculatofortests.store.StoreImpl
import com.haretskiy.pavel.calculatofortests.views.CalculatorView
import kotlinx.android.synthetic.main.activity_main.*


class CalculatorActivity : AppCompatActivity(), View.OnClickListener, CalculatorView {

    private var isHistoryVisible = false

    private val calculator: Calculator by lazy {
        CalculatorImpl()
    }

    private val store: Store by lazy {
        StoreImpl(Room.databaseBuilder(applicationContext, Database::class.java, DB_NAME).allowMainThreadQueries().build().storeDao())
    }

    private val presenter: CalculatorPresenter by lazy {
        CalculatorPresenterImpl(calculator, store)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
        initListView()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onClick(v: View?) {
        when (v) {
            bt0 -> presenter.clickNumber(0)
            bt1 -> presenter.clickNumber(1)
            bt2 -> presenter.clickNumber(2)
            bt3 -> presenter.clickNumber(3)
            bt4 -> presenter.clickNumber(4)
            bt5 -> presenter.clickNumber(5)
            bt6 -> presenter.clickNumber(6)
            bt7 -> presenter.clickNumber(7)
            bt8 -> presenter.clickNumber(8)
            bt9 -> presenter.clickNumber(9)

            btAdd -> presenter.clickOperation(CODE_ADD)
            btDiv -> presenter.clickOperation(CODE_DIVIDE)
            btMult -> presenter.clickOperation(CODE_MULTIPLE)
            btSub -> presenter.clickOperation(CODE_SUB)

            btIs -> presenter.clickResult()
            btClearLast -> presenter.clickClearLast()
            btClearAll -> presenter.clickClearAll()
            btHistory -> presenter.clickHistory()

        }
    }

    override fun setVisibilityOfHistory() {
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

    override fun printOnCalculatorDisplay(text: String) {
        calculatorDisplay.text = text
    }

    override fun setHistoryContent(history: Array<String>) {
        tvHistory.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history)
    }

    private fun initListView() {
        tvHistory.setOnItemClickListener { _, _, position, _ ->
            presenter.clickHistory()
            presenter.clickHistoryItem(position)
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

}
