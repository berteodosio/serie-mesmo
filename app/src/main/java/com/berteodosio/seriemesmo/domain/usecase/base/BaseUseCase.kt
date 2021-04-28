package com.berteodosio.seriemesmo.domain.usecase.base

import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase() {

    fun <T> Single<T>.onErrorLogException(): Single<T> = this.doOnError {
        AppLogger.e(this@BaseUseCase.TAG, "Error running [${this@BaseUseCase.TAG}]", it)
    }

}

fun <T> Single<T>.setupCommonSchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.setupCommonSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}