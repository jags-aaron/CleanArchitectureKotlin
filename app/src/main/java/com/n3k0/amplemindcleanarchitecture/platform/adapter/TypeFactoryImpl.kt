package com.n3k0.amplemindcleanarchitecture.platform.adapter

import android.view.View
import com.n3k0.amplemindcleanarchitecture.R
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.TypeFactory
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewHolder
import com.n3k0.amplemindcleanarchitecture.platform.view.main.HolderCountryItem

class TypeFactoryImpl : TypeFactory {

    override fun type(itemPresenter: MainItemPresenter) = R.layout.item_country

    override fun holder(type: Int, view: View): BaseViewHolder<*, *> {
        return when(type) {
            R.layout.item_country -> HolderCountryItem(view)
            else -> throw RuntimeException("Wrong view type")
        }
    }
}
