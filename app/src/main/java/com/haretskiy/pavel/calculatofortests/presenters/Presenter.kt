package com.haretskiy.pavel.calculatofortests.presenters

interface Presenter<in T> {
    fun attachView(view: T)
    fun detachView()
}