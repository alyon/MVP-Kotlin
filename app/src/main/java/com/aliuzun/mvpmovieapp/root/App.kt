package com.aliuzun.mvpmovieapp.root

import android.app.Application
import com.aliuzun.mvpmovieapp.http.ApiModuleForName
import com.aliuzun.mvpmovieapp.topmovies.TopMoviesModule




class App : Application() {

    internal lateinit var component: ApplicationComponent

    fun getComponent(): ApplicationComponent {
        return component
    }
    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule( ApplicationModule(this))
                .apiModuleForName( ApiModuleForName())
                .topMoviesModule( TopMoviesModule())
                .build()
    }
}