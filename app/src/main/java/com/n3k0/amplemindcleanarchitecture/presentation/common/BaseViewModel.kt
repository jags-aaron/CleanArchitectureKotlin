package com.n3k0.amplemindcleanarchitecture.presentation.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    abstract fun onViewReady()
}
