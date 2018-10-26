package com.n3k0.amplemindcleanarchitecture.presentation.common

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.ItemPresenter

abstract class BaseViewHolder<in IP: ItemPresenter, in VM: ViewModel>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(viewViewModel: VM, itemPresenter: IP, position: Int)
}