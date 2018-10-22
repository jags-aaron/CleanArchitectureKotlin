package com.n3k0.amplemindcleanarchitecture.presentation.boundary

import android.view.View
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewHolder
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter

interface TypeFactory {
    fun type(itemPresenter: MainItemPresenter): Int

    fun holder(type: Int, view: View): BaseViewHolder<*, *>
}
