package com.aliuzun.mvpmovieapp.topmovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.aliuzun.mvpmovieapp.R
import kotlinx.android.synthetic.main.activity_main.*
import com.aliuzun.mvpmovieapp.root.App
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DefaultItemAnimator
import javax.inject.Inject

class TopMoviesActivity : AppCompatActivity(), TopMoviesActivityMVP.View {


    val TAG = TopMoviesActivity::class.java.name

    lateinit var listAdapter: ListAdapter
    var resultList = ArrayList<com.aliuzun.mvpmovieapp.topmovies.ViewModel>()

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
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadData()
    }

    override fun onStop() {
        super.onStop()
        presenter.rxUnsubscribe()
        resultList.clear()
        listAdapter.notifyDataSetChanged()
    }

    override fun updateData(viewModel: ViewModel) {
        resultList.add(viewModel)
        listAdapter.notifyItemInserted(resultList.size - 1)
    }

    override fun showSnackBar(msg: String) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show()
    }
}
