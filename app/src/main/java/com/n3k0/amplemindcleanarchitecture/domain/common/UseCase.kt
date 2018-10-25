package com.n3k0.amplemindcleanarchitecture.domain.common

import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor

abstract class UseCase(private val executor: Executor) {

    fun uiExecute(uiFun: suspend () -> Unit) {
        executor.uiExecute { uiFun() }
    }

    fun asyncExecute(asyncFun: suspend () -> Unit) {
        executor.asyncExecute { asyncFun() }
    }

    fun cancelJob(){
        executor.cancelJob()
    }

}