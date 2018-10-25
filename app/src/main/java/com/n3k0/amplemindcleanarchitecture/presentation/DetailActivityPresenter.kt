package com.n3k0.amplemindcleanarchitecture.presentation

import com.n3k0.amplemindcleanarchitecture.presentation.boundary.DetailView
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewModel

class DetailActivityPresenter(
    val view: DetailView,
    val flag: String,
    val name: String
) : BaseViewModel() {

    override fun onViewReady() {
        view.showFlag(flag)
        view.changeText(name)
    }

}