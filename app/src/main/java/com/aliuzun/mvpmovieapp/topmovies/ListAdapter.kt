package com.aliuzun.mvpmovieapp.topmovies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aliuzun.mvpmovieapp.R

class ListAdapter(var listItems: List<ViewModel>) : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {

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
      holder.titleText.text = listItems[position].name
      holder.descText.text = listItems[position].country
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleText by lazy { itemView!!.findViewById<TextView>(R.id.movieTitle) }
        val descText by lazy { itemView!!.findViewById<TextView>(R.id.movieDesc) }
    }
}


