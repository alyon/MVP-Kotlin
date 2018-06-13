package com.aliuzun.mvpmovieapp.topmovies

import com.aliuzun.mvpmovieapp.http.MoreInfoApiService
import com.aliuzun.mvpmovieapp.http.MovieApiService
import com.aliuzun.mvpmovieapp.http.apimodel.OmdbApi
import com.aliuzun.mvpmovieapp.http.apimodel.Result
import com.aliuzun.mvpmovieapp.http.apimodel.TopRated

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

class TopMoviesRepository(private val movieApiService: MovieApiService, private val moreInfoApiService: MoreInfoApiService) : Repository {
    private val countries: MutableList<String>
    private val results: MutableList<Result>
    private var timestamp: Long = 0

    companion object {
        private val STALE_MS = (20 * 1000).toLong() // Data is stale after 20 seconds
    }

    init {
        this.timestamp = System.currentTimeMillis()
        countries = ArrayList()
        results = ArrayList()
    }

    val isUpToDate: Boolean
        get() = System.currentTimeMillis() - timestamp < STALE_MS

    override val resultsFromMemory: Observable<Result>
        get() {
            if (isUpToDate) {
                return Observable.fromIterable(results)
            } else {
                timestamp = System.currentTimeMillis()
                results.clear()
                return Observable.empty()
            }
        }

    override val resultsFromNetwork: Observable<Result>
        get() {

            val topRatedObservable = movieApiService.topRatedMovies(Integer(1)).
                    concatWith(movieApiService.topRatedMovies(Integer(2))).
                    concatWith(movieApiService.topRatedMovies(Integer(3)))

            return topRatedObservable.concatMap { topRated -> Observable.fromIterable(topRated.results) }.doOnNext { result -> results.add(result) }
        }

    override val countriesFromMemory: Observable<String>
        get() {
            if (isUpToDate) {
                return Observable.fromIterable(countries)
            } else {
                timestamp = System.currentTimeMillis()
                countries.clear()
                return Observable.empty()
            }
        }

    override val countriesFromNetwork: Observable<String>
        get() = resultsFromNetwork.concatMap { result ->
            println("countriesFromNetwork result $result")
            moreInfoApiService.getCountry(result.title!!) }.concatMap {
            omdbApi ->
            println("countriesFromNetwork omdbApi $omdbApi")

                Observable.just(omdbApi.country?:"Unknown")
        }.doOnNext { s ->
            println("countriesFromNetwork doOnNext $s")
            countries.add(s) }

    override val countryData: Observable<String>
        get() = countriesFromMemory.switchIfEmpty(countriesFromNetwork)

    override val resultData: Observable<Result>
        get() = resultsFromMemory.switchIfEmpty(resultsFromNetwork)

}
