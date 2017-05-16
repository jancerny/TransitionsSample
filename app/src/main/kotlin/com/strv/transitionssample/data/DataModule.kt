package com.strv.transitionssample.data

import com.squareup.moshi.Moshi
import com.strv.transitionssample.TransitionsConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val httpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .addInterceptor {
                chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter(The500PxApi.CONSUMER_KEY, TransitionsConfig.THE_500PX_API_KEY)
                        .build()
                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()

                chain.proceed(request)
            }
            .build()
}

val moshi: Moshi by lazy {
    Moshi.Builder()
            .build()
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
            .client(httpClient)
            .baseUrl(TransitionsConfig.THE_500PX_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}

val api: The500PxApi by lazy {
    retrofit.create(The500PxApi::class.java)
}

val cache: Cache by lazy { Cache() }