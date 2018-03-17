package me.gookven.commonsense.modules

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Serialization {
    @Provides
    @Singleton
    fun defaultGson() =
            GsonBuilder()
            .create()
}