package com.aliuzun.mvpmovieapp.topmovies

import io.reactivex.internal.disposables.DisposableHelper.isDisposed
import io.reactivex.observers.DisposableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.Disposable


class TopMoviesPresenter(private val model: TopMoviesActivityMVP.Model) : TopMoviesActivityMVP.Presenter {

    private var view: TopMoviesActivityMVP.View? = null
    private var subscription: Disposable? = null

    override fun loadData() {

        subscription = model
                .result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ViewModel>() {
                    override fun onComplete() {
                        view!!.showSnackBar("Data onCompleted")
                    }


                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        if (view != null) {
                            view!!.showSnackBar("Error getting movies")
                        }
                    }

                    override fun onNext(viewModel: ViewModel) {
                        if (view != null) {
                            view!!.updateData(viewModel)
                        }
                    }
                })
    }

    override fun rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription!!.isDisposed) {
                subscription!!.dispose()
            }
        }
    }

    override fun setView(view: TopMoviesActivityMVP.View) {
        this.view = view
    }

}