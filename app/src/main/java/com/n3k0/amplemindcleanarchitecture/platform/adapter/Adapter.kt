package com.n3k0.amplemindcleanarchitecture.platform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.ItemPresenter
import com.n3k0.amplemindcleanarchitecture.presentation.boundary.TypeFactory
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewModel
import com.n3k0.amplemindcleanarchitecture.presentation.common.BaseViewHolder

@Suppress("UNCHECKED_CAST")
class Adapter(
    private val items: List<ItemPresenter>,
    private val viewModel: ViewModel,
    private val typeFactory: TypeFactory
) : RecyclerView.Adapter<BaseViewHolder<ItemPresenter, ViewModel>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<ItemPresenter, ViewModel>, position: Int) {
        holder.bind(viewModel, items[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemPresenter, ViewModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typeFactory.holder(viewType, view) as BaseViewHolder<ItemPresenter, ViewModel>
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type(typeFactory)
    }
}