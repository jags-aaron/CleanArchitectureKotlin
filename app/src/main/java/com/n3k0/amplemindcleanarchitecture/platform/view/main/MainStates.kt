package com.n3k0.amplemindcleanarchitecture.platform.view.main

import com.n3k0.amplemindcleanarchitecture.data.model.Country
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter

data class MainStates(
    val progress: Boolean = false,
    val countryList: List<MainItemPresenter> = emptyList(),
    val error: Exception? = null,
    val countrySelected: Country? = null
)