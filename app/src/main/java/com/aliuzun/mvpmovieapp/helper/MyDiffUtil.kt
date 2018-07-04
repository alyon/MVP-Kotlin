package com.aliuzun.mvpmovieapp.helper

import android.support.v7.util.DiffUtil
import com.aliuzun.mvpmovieapp.http.apimodel.Result

class MyDiffUtil( var oldList :List<Result>,  var newList :List<Result>): DiffUtil.Callback() {


    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[0].id == newList[0].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition] == newList[newItemPosition]
    }
}