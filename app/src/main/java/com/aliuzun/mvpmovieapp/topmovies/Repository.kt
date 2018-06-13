package com.aliuzun.mvpmovieapp.topmovies

import com.aliuzun.mvpmovieapp.http.apimodel.Result
import io.reactivex.Observable

interface Repository {

    val resultsFromMemory: Observable<Result>

    val resultsFromNetwork: Observable<Result>

    val countriesFromMemory: Observable<String>

    val countriesFromNetwork: Observable<String>

    val countryData: Observable<String>

    val resultData: Observable<Result>

}

