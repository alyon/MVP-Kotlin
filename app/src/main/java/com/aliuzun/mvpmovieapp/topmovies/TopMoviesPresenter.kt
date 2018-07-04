package com.aliuzun.mvpmovieapp.topmovies

import android.arch.lifecycle.ViewModel
import com.aliuzun.mvpmovieapp.http.MovieApiService
import com.aliuzun.mvpmovieapp.http.apimodel.TopRated
import io.reactivex.internal.disposables.DisposableHelper.isDisposed
import io.reactivex.observers.DisposableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.util.NotificationLite.subscription


class TopMoviesPresenter(private val movieApiService: MovieApiService) : TopMoviesActivityMVP.Presenter {

    private var view: TopMoviesActivityMVP.View? = null
    private var subscription: CompositeDisposable = CompositeDisposable()

    override fun loadData() {

        if (view != null) view!!.setLoading(true)

        subscription.add(movieApiService.topRatedMovies(Integer(1)).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                doOnTerminate({
                  if (view != null) {
                      view!!.setLoading(false)
                  }})
                .subscribe({ topRated ->
                    if (view != null) {
                        view!!.updateData(topRated.results)
                    }
                }, { error ->
                    if (view != null) {
                        view!!.showSnackBar(error.localizedMessage)
                    }
                })
        )
    }

    override fun detachView() {
        subscription.clear()
    }

    override fun attachView(view: TopMoviesActivityMVP.View) {
        this.view = view
    }

}