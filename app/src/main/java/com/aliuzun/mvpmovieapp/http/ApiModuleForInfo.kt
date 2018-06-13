package com.aliuzun.mvpmovieapp.http

import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


@Module
class ApiModuleForInfo {
    val BASE_URL = "http://www.omdbapi.com"
    val API_KEY = "bb8fc02"


    @Provides
    fun provideClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter(
                    "apikey",
                    API_KEY
            ).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
    }

    @Provides
    fun provideRetrofit(baseURL: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun provideApiService(): MoreInfoApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(MoreInfoApiService::class.java!!)
    }

}