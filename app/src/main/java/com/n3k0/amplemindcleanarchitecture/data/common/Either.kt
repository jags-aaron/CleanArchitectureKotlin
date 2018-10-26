package com.n3k0.amplemindcleanarchitecture.data.common

sealed class Either<out L, out R> {
    //Failure
    data class Left<out L>(val value: L) : Either<L, Nothing>()

    //Success
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)
}
