package dev.software.apicorutineroom

import android.graphics.Rect
import android.view.View

import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow


/*
fun RecyclerView.addPaginationScrollListener(
    layoutManager: LinearLayoutManager,
    itemsToLoad: Int,
    loadMore: () -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItems = layoutManager.itemCount
            println("totalItemCount $totalItems")

            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            println("lastVisibleItem $lastVisibleItem")

            if (dy != 0 && totalItems <= (lastVisibleItem + itemsToLoad)) {
                recyclerView.post(loadMore)
            }
        }
    })
}*/



fun RecyclerView.addSpaceDecoration(bottomSpace: Int) {

    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemCount = parent.adapter?.itemCount ?: return
            val position = parent.getChildAdapterPosition(view)
            if (position != itemCount - 1) {
                outRect.bottom = bottomSpace
            }

        }
    })
}

fun SwipeRefreshLayout.onRefreshListener() = callbackFlow {
    setOnRefreshListener {
        trySend(Unit)
    }

    awaitClose {
        setOnRefreshListener(null)
    }
}

fun Toolbar.onSearchQueryChanged() = callbackFlow {
    val searchView = menu.findItem(R.id.search).actionView as SearchView

    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            trySend(newText)
            return true
        }
    }

    searchView.setOnQueryTextListener(queryTextListener)

    awaitClose {
        searchView.setOnQueryTextListener(null)
    }
}


fun RecyclerView.onPaginationScrollListener(
    layoutManager: LinearLayoutManager,
    itemsToLoad: Int
) = callbackFlow {
    val scrollListener = PagingScrollListener(layoutManager, itemsToLoad) {
        trySend(Unit)
    }
    addOnScrollListener(scrollListener)

    awaitClose {
        removeOnScrollListener(scrollListener)
    }
}

class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val itemsToLoad: Int,
    private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        if (dy != 0 && totalItemCount <= (lastVisibleItem + itemsToLoad)) {
            recyclerView.post(onLoadMore)
        }
    }
}