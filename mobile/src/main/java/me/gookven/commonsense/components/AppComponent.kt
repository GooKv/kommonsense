package me.gookven.commonsense.components

import dagger.Component
import me.gookven.commonsense.activity.CommentsActivity
import me.gookven.commonsense.modules.ApiConsumers
import me.gookven.commonsense.modules.Serialization
import me.gookven.commonsense.modules.Transport
import me.gookven.commonsense.mvp.presenter.CommentsPresenter
import javax.inject.Singleton

@Component(modules = arrayOf(
        Serialization::class,
        Transport::class,
        ApiConsumers::class
))
@Singleton
interface AppComponent {
    fun inject(commentsPresenter: CommentsPresenter)
    fun inject(commentsActivity: CommentsActivity)
}