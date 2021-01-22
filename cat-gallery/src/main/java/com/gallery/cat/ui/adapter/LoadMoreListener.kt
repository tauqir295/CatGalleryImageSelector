package com.gallery.cat.ui.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * class for checking the scroll to last element in the recycler view
 */
class LoadMoreListener(
    private val gridLayoutManager: GridLayoutManager,
    private val listener: OnLoadMoreListener?
) : RecyclerView.OnScrollListener() {
    private var loading: Boolean = false // load more progress dialog

    //default constructor
    companion object {
        private const val VISIBLE_THRESHOLD = 2
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dx == 0 && dy == 0)
            return
        val totalItemCount = gridLayoutManager.itemCount
        val lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()

        // checking the condition if user has scrolled to last item in recycler-view or not
        if (!loading && totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD && totalItemCount != 0) {
            listener?.onLoadMore()
            loading = true
        }
    }

    /**
     * setting the variable to enable [OnLoadMoreListener.onLoadMore] or not
     */
    fun setLoaded() {
        loading = false
    }

    /**
     * custom interface to pass event to load more data or not
     */
    interface OnLoadMoreListener {
        fun onLoadMore()
    }

}