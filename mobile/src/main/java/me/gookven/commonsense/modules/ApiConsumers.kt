package me.gookven.commonsense.modules

import dagger.Module
import dagger.Provides
import me.gookven.commonsense.api.PlaceholderApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(
        Transport::class
))
class ApiConsumers {
    @Provides
    @Singleton
    fun placeholderApi(
            retrofitBuilder: Retrofit.Builder = Transport().retrofitBuilder()
    ) = retrofitBuilder
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(PlaceholderApi::class.java)
}