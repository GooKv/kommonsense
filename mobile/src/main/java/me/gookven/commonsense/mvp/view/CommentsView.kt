package me.gookven.commonsense.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import me.gookven.commonsense.api.dto.Comment

interface CommentsView : MvpView, ErrorHandler {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderComments(comments: List<Comment>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    override fun handleError(exception: Throwable)
}