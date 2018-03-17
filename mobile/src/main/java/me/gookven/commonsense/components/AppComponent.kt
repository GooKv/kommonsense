package me.gookven.commonsense.components

import dagger.Component
import me.gookven.commonsense.activity.MainActivity
import me.gookven.commonsense.modules.ApiConsumers
import me.gookven.commonsense.modules.Serialization
import me.gookven.commonsense.modules.Transport
import javax.inject.Singleton

@Component(modules = arrayOf(
        Serialization::class,
        Transport::class,
        ApiConsumers::class
))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}