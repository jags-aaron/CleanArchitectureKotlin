package com.n3k0.amplemindcleanarchitecture.presentation

import com.n3k0.amplemindcleanarchitecture.presentation.boundary.DetailView
import com.n3k0.amplemindcleanarchitecture.presentation.common.BasePresenter

class DetailActivityPresenter(
    val view: DetailView,
    val flag: String,
    val name: String
) : BasePresenter() {

    override fun onViewReady() {
        view.showFlag(flag)
        view.changeText(name)
    }

}