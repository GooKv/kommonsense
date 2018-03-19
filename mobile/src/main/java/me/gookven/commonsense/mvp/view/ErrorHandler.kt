package me.gookven.commonsense.mvp.view

interface ErrorHandler {
    fun handleError(exception: Throwable)
}