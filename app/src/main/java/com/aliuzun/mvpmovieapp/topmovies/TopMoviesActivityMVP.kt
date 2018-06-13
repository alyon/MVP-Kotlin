package com.aliuzun.mvpmovieapp.topmovies

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

interface TopMoviesActivityMVP {
    interface View{
        fun updateData(viewModel:com.aliuzun.mvpmovieapp.topmovies.ViewModel)
        fun showSnackBar(s:String)
    }
    interface Presenter{
        fun loadData()
        fun rxUnsubscribe()
        fun setView(view: View)
    }
    interface Model{
        fun result():Observable<com.aliuzun.mvpmovieapp.topmovies.ViewModel>
    }
}