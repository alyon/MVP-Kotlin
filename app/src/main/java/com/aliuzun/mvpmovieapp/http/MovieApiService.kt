package com.aliuzun.mvpmovieapp.http

import com.aliuzun.mvpmovieapp.http.apimodel.TopRated
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("top_rated")
    fun topRatedMovies (@Query("page") pag:Integer): Observable<TopRated>
}