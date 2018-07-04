package com.aliuzun.mvpmovieapp.topmovies

import com.aliuzun.mvpmovieapp.http.MovieApiService
import dagger.Provides
import javax.inject.Singleton
import dagger.Module


@Module
class TopMoviesModule {

    @Provides
    fun provideTopMoviesActivityPresenter(movieApiService: MovieApiService): TopMoviesActivityMVP.Presenter {
        return TopMoviesPresenter(movieApiService)
    }

}