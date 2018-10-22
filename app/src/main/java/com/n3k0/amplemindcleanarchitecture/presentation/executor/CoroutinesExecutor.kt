package com.n3k0.amplemindcleanarchitecture.presentation.executor

import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CoroutinesExecutor : Executor {

    private lateinit var deferred: Deferred<Unit>

    override fun uiExecute(uiFun: suspend () -> Unit) {
        launch(UI) {
            uiFun()
        }
    }

    override fun asyncExecute(asyncFun: suspend () -> Unit) {
        deferred = async(CommonPool) {
            asyncFun()
        }
    }

}