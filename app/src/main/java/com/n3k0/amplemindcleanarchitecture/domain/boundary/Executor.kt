package com.n3k0.amplemindcleanarchitecture.domain.boundary

interface Executor {
    fun uiExecute(uiFun: suspend () -> Unit)
    fun asyncExecute(asyncFun: suspend () -> Unit)
}