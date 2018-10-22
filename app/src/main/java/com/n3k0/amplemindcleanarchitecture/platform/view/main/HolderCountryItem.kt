package com.n3k0.amplemindcleanarchitecture.platform.view.main

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewHolder
import com.n3k0.amplemindcleanarchitecture.presentation.MainActivityPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter

class HolderCountryItem(val view: View) : BaseViewHolder<MainItemPresenter, MainActivityPresenter>(view) {

    override fun bind(viewPresenter: MainActivityPresenter, itemPresenter: MainItemPresenter, position: Int) {
        view.findViewById<ConstraintLayout>(R.id.itemContainer).setOnClickListener {
            viewPresenter.onItemClicked(itemPresenter.country)
        }

        view.findViewById<TextView>(R.id.tvTitle).text = itemPresenter.getCountryName()
    }
}