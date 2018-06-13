package com.aliuzun.mvpmovieapp.topmovies

import com.aliuzun.mvpmovieapp.http.apimodel.Result
import io.reactivex.Observable
import io.reactivex.functions.BiFunction


class TopMoviesModel(private val repository: Repository) : TopMoviesActivityMVP.Model {

    override fun result(): Observable<ViewModel> {
        return Observable.zip(
                repository.resultData,
                repository.countryData,
                BiFunction<Result, String, ViewModel> { result, s -> ViewModel(result.title, s) }
        )
    }

}