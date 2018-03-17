package me.gookven.commonsense

import me.gookven.commonsense.components.AppComponent
import me.gookven.commonsense.components.DaggerAppComponent
import me.gookven.commonsense.modules.ApiConsumers
import me.gookven.commonsense.modules.Serialization
import me.gookven.commonsense.modules.Transport

class App {
    companion object {
        val component: AppComponent = DaggerAppComponent.builder()
                .serialization(Serialization())
                .transport(Transport())
                .apiConsumers(ApiConsumers())
                .build()
    }
}