package com.aliuzun.mvpmovieapp.topmovies

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aliuzun.mvpmovieapp.R
import com.aliuzun.mvpmovieapp.helper.MyDiffUtil
import com.aliuzun.mvpmovieapp.http.apimodel.Result
import com.aliuzun.mvpmovieapp.http.apimodel.TopRated

class ListAdapter(var listItems: MutableList<Result>) : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        // LayoutInflater: takes ID from layout defined in XML.
        // Instantiates the layout XML into corresponding View objects.
        // Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.movie_list_row, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
      holder.titleText.text = listItems[position].title
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun updateDataList(newList:List<Result>){
        val diffUtil = MyDiffUtil(listItems, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listItems.clear()
        listItems.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText by lazy { itemView!!.findViewById<TextView>(R.id.movieTitle) }
    }
}


