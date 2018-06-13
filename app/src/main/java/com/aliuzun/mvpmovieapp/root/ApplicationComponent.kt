package com.aliuzun.mvpmovieapp.root

import com.aliuzun.mvpmovieapp.http.ApiModuleForInfo
import com.aliuzun.mvpmovieapp.http.ApiModuleForName
import com.aliuzun.mvpmovieapp.topmovies.TopMoviesActivity
import com.aliuzun.mvpmovieapp.topmovies.TopMoviesModule
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(ApplicationModule::class),(ApiModuleForName::class), (ApiModuleForInfo::class), (TopMoviesModule::class)])
interface ApplicationComponent {
    fun inject(target: TopMoviesActivity)
}