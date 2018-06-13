package com.aliuzun.mvpmovieapp.http.apimodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

data class TopRated(
        @SerializedName("page") var page: Int? = null,
        @SerializedName("results") var results: List<Result> = ArrayList(),
        @SerializedName("total_results") var totalResults: Int? = null,
        @SerializedName("total_pages") var totalPages: Int? = null
)


