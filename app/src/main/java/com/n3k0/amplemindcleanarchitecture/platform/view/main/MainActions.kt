package com.n3k0.amplemindcleanarchitecture.platform.view.main

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.MxViewAction

sealed class MainActions: MxViewAction {
    data class InitialAction(val inProgress: Boolean): MainActions()
    data class ErrorAction(val failure: Exception): MainActions()
    data class SuccessAction(val countries: List<Country>): MainActions()
    data class ShowDetailAction(val country: Country): MainActions()
}