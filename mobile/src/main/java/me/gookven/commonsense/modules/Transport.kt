package me.gookven.commonsense.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.gookven.commonsense.modules.interceptors.RxSchedulingWrapper
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(
        Serialization::class
))
class Transport {
    @Provides
    @Singleton
    fun converterFactory(
            gson: Gson = Serialization().defaultGson()
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun ioAdapterFactory(): CallAdapter.Factory =
            RxSchedulingWrapper(
                    RxJava2CallAdapterFactory.create(),
                    Schedulers.io(),
                    AndroidSchedulers.mainThread()
            )

    @Provides
    @Singleton
    fun auth(): Authenticator = Authenticator.NONE

    @Provides
    @Singleton
    fun client(
            auth: Authenticator = auth()
    ): OkHttpClient = OkHttpClient.Builder()
            .authenticator(auth)
            //.addNetworkInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun retrofitBuilder(
            converterFactory: Converter.Factory = converterFactory(),
            ioAdapterFactory: CallAdapter.Factory = ioAdapterFactory(),
            client: OkHttpClient = client()
    ): Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(ioAdapterFactory)
            .client(client)
}