package com.n3k0.amplemindcleanarchitecture.presentation.executor

import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

class CoroutinesExecutor : Executor {

    private lateinit var deferred: Deferred<Unit>
    private lateinit var job: Job


    override fun uiExecute(uiFun: suspend () -> Unit) {
        job = launch(UI) {
            uiFun()
        }
    }

    override fun asyncExecute(asyncFun: suspend () -> Unit) {
        deferred = async(CommonPool) {
            asyncFun()
        }
    }

    override fun cancelJob() {
        deferred.cancel()
        job.cancel()
    }

}