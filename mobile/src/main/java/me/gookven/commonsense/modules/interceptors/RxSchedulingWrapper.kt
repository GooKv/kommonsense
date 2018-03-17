package me.gookven.commonsense.modules.interceptors

import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

/**
 * Rx call adapter factory with controlled subscription/observation schedulers
 */
class RxSchedulingWrapper(
        private val rxAdapter: RxJava2CallAdapterFactory,
        val subscribeScheduler: Scheduler,
        val observerScheduler: Scheduler
) : CallAdapter.Factory() {
    @Suppress("UNCHECKED_CAST")
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val delegateAdapter = rxAdapter.get(returnType, annotations, retrofit)
        return ThreadCallAdapter(delegateAdapter as CallAdapter<Any, Observable<Any>>)
    }

    internal inner class ThreadCallAdapter<T>(
            private val delegateAdapter: CallAdapter<T, Observable<T>>
    ) : CallAdapter<T, Observable<T>> by delegateAdapter {
        override fun adapt(call: Call<T>): Observable<T> {
            return delegateAdapter
                    .adapt(call)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observerScheduler)
        }
    }
}
