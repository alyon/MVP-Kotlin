package com.aliuzun.mvpmovieapp.topmovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import com.aliuzun.mvpmovieapp.R
import com.aliuzun.mvpmovieapp.root.App
import android.support.v7.widget.LinearLayoutManager
import com.aliuzun.mvpmovieapp.R.id.*
import com.aliuzun.mvpmovieapp.helper.DividerItemDecoration

import com.aliuzun.mvpmovieapp.http.apimodel.Result
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TopMoviesActivity : AppCompatActivity(), TopMoviesActivityMVP.View {

    val TAG = TopMoviesActivity::class.java.name

    lateinit var listAdapter: ListAdapter
    var resultList = ArrayList<Result>()

    @Inject
    lateinit var presenter: TopMoviesActivityMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)

        listAdapter = ListAdapter(resultList)
        recyclerView.adapter = listAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshView.setOnRefreshListener { presenter.loadData() }
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        resultList.clear()
        presenter.detachView()

    }

    override fun updateData(results: List<Result>) {
        listAdapter.updateDataList(results)
    }

    override fun showSnackBar(msg: String) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun setLoading(isLoading: Boolean) {
        swipeRefreshView.isRefreshing = isLoading
    }

}
