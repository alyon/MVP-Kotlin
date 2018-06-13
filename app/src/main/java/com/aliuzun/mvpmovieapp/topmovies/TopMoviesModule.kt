package com.aliuzun.mvpmovieapp.topmovies

import com.aliuzun.mvpmovieapp.http.MoreInfoApiService
import com.aliuzun.mvpmovieapp.http.MovieApiService
import dagger.Provides
import javax.inject.Singleton
import dagger.Module


@Module
class TopMoviesModule {

    @Provides
    fun provideTopMoviesActivityPresenter(topMoviesModel: TopMoviesActivityMVP.Model): TopMoviesActivityMVP.Presenter {
        return TopMoviesPresenter(topMoviesModel)
    }

    @Provides
    fun provideTopMoviesActivityModel(repository: Repository): TopMoviesActivityMVP.Model {
        return TopMoviesModel(repository)
    }

    @Singleton
    @Provides
    fun provideRepo(movieApiService: MovieApiService, moreInfoApiService: MoreInfoApiService): Repository {
        return TopMoviesRepository(movieApiService, moreInfoApiService)
    }


}