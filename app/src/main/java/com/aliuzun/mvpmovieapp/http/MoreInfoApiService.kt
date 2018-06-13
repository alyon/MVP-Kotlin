package com.aliuzun.mvpmovieapp.http

import com.aliuzun.mvpmovieapp.http.apimodel.OmdbApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoreInfoApiService {

    @GET("/")
    fun getCountry(@Query("t") title:String): Observable<OmdbApi>?
}