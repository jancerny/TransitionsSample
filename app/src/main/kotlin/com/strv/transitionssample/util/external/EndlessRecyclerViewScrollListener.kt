package com.strv.transitionssample.util.external

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

typealias Action = (page: Int, totalItemsCount: Int) -> Unit



fun RecyclerView.onLoadMore(action: Action) {
    layoutManager.apply {
        val listener = when (this) {
            is StaggeredGridLayoutManager -> object : EndlessRecyclerViewScrollListener(this) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    action(page, totalItemsCount)
                }
            }
            is GridLayoutManager -> object : EndlessRecyclerViewScrollListener(this) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    action(page, totalItemsCount)
                }
            }
            is LinearLayoutManager -> object : EndlessRecyclerViewScrollListener(this) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    action(page, totalItemsCount)
                }
            }
            else -> null
        }
        listener?.apply {
            addOnScrollListener(this)
        }
    }
}