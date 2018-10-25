package com.n3k0.amplemindcleanarchitecture.platform.view.main

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewHolder
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityViewModel
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter

class HolderCountryItem(val view: View) : BaseViewHolder<MainItemPresenter, MainActivityViewModel>(view) {

    override fun bind(viewViewModel: MainActivityViewModel, itemPresenter: MainItemPresenter, position: Int) {
        view.findViewById<ConstraintLayout>(R.id.itemContainer).setOnClickListener {
            viewViewModel.onItemClicked(itemPresenter.country)
        }

        view.findViewById<TextView>(R.id.tvTitle).text = itemPresenter.getCountryName()
    }
}