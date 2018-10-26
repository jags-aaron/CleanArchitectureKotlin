package com.n3k0.amplemindcleanarchitecture.platform.view.main

import javax.inject.Inject

class MainRender @Inject internal constructor() {

    fun layout(view: MainView, state: MainStates) {
        view.onStateChange(state)
    }
}