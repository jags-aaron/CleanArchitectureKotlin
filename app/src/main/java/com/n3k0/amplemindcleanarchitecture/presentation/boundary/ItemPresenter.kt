package com.n3k0.amplemindcleanarchitecture.presentation.boundary

interface ItemPresenter {
    fun type(typeFactory: TypeFactory) : Int
}