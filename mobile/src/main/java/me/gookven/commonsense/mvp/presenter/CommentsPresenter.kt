package me.gookven.commonsense.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import me.gookven.commonsense.App
import me.gookven.commonsense.api.PlaceholderApi
import me.gookven.commonsense.mvp.view.CommentsView
import javax.inject.Inject

@InjectViewState
class CommentsPresenter : MvpPresenter<CommentsView>() {
    @Inject lateinit var placeholderApi: PlaceholderApi

    init {
        App.component.inject(this)
        loadComments()
    }

    fun loadComments() {
        placeholderApi
                .comments()
                .subscribe(viewState::renderComments, viewState::handleError)
    }
}
