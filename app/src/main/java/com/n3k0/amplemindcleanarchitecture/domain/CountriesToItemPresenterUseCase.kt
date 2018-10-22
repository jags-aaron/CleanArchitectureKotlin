package com.n3k0.amplemindcleanarchitecture.domain

import com.n3k0.amplemindcleanarchitecture.data.model.Country

import com.n3k0.amplemindcleanarchitecture.domain.boundary.Executor
import com.n3k0.amplemindcleanarchitecture.domain.boundary.UseCaseFactory
import com.n3k0.amplemindcleanarchitecture.domain.common.UseCase
import com.n3k0.amplemindcleanarchitecture.presentation.MainItemPresenter

class CountriesToItemPresenterUseCase(
    executor: Executor,
    private val countries: List<Country>,
    private val useCaseFactory: UseCaseFactory
) : UseCase(executor) {

    fun execute(onResult: (List<MainItemPresenter>) -> Unit) {
        asyncExecute {
            // Asynchronous process
            val result = countries.map {
                MainItemPresenter(it, useCaseFactory)
            }

            uiExecute {
                // Synchronous process
                onResult(result)
            }
        }
    }
}