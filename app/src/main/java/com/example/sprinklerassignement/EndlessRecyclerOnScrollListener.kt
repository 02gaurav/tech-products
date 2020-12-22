package com.example.sprinklerassignement

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener {

    companion object {
        private const val VISIBLE_THRESHOLD_LIST = 3
    }

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private var visibleThreshold = 3 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    var currentPage = 1

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var isUseLinearLayoutManager: Boolean = false

    constructor(layoutManager: RecyclerView.LayoutManager?) {
        this.mLayoutManager = layoutManager
        when (layoutManager) {
            is LinearLayoutManager -> {
                isUseLinearLayoutManager = true
                visibleThreshold = VISIBLE_THRESHOLD_LIST
            }
        }
    }

    constructor(linearLayoutManager: LinearLayoutManager?) {
        this.mLayoutManager = linearLayoutManager
        isUseLinearLayoutManager = true
        visibleThreshold = VISIBLE_THRESHOLD_LIST

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        try {
            visibleItemCount = recyclerView.childCount
            totalItemCount = mLayoutManager?.itemCount ?: 0


            if (isUseLinearLayoutManager && mLayoutManager is LinearLayoutManager) {
                firstVisibleItem = (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            }

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                // Do something
                currentPage++

                onLoadMore(currentPage)

                loading = true
            }
        } catch (e: Exception) {
            loading = false
        }
    }

    abstract fun onLoadMore(currentPage: Int)

    fun reset() {
        currentPage = 1
        previousTotal = 0
        loading = true
    }

    fun destroy() {
        mLayoutManager = null
    }
}