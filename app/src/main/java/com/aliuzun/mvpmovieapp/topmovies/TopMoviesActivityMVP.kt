package com.aliuzun.mvpmovieapp.topmovies

import android.arch.lifecycle.ViewModel
import com.aliuzun.mvpmovieapp.http.apimodel.Result
import io.reactivex.Observable

interface TopMoviesActivityMVP {
    interface View{
        fun updateData(results:List<Result>)
        fun showSnackBar(s:String)
        fun setLoading(isLoading: Boolean)
    }
    interface Presenter{
        fun loadData()
        fun detachView()
        fun attachView(view: View)
    }

}