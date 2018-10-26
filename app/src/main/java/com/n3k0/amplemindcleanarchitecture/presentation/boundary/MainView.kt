package com.n3k0.amplemindcleanarchitecture.presentation.boundary

import com.n3k0.amplemindcleanarchitecture.platform.view.main.MainStates

interface MainView {
    fun onStateChange(state: MainStates)
}