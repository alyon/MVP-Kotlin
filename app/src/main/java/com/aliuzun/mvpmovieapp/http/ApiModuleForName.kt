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
class ApiModuleForName {
    val BASE_URL = "http://api.themoviedb.org/3/movie/"
    val API_KEY = "8551c026bbf22a4a386ebb5b87a5296b"


    @Provides
    fun provideClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder().
                addInterceptor(interceptor).
                addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter(
                    "api_key",
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
    fun provideApiService(): MovieApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(MovieApiService::class.java)
    }
}