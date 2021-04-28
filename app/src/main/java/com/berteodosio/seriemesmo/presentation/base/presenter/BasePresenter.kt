package com.berteodosio.seriemesmo.presentation.base.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {

    private val pendingDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        pendingDisposable.add(disposable)
    }

    fun onDestroy() {
        pendingDisposable.clear()
    }

}