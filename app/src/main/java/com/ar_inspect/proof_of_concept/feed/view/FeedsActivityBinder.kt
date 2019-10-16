package com.ar_inspect.proof_of_concept.feed.view

import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ar_inspect.proof_of_concept.BR
import com.ar_inspect.proof_of_concept.R
import com.ar_inspect.proof_of_concept.databinding.ActivityFeedBinding
import com.ar_inspect.proof_of_concept.feed.model.FeedInfoDto
import com.awesome_lib.core.AbstractBinding
import com.awesome_lib.core.adapters.BindingRecyclerAdapter

/**
 * [FeedsActivityBinder] :
 *
 * [AbstractBinding] class provides data-binding logic to particular view to move UI logic off the Activity/Fragment.
 * This binder binds list of `Feeds` to `RecyclerView` and handles swipe refresh states.
 *
 * @author : Jeel Vankhede
 * @version 1.0.0
 * @since 10/16/2019
 *
 * @see AbstractBinding
 */
class FeedsActivityBinder() : AbstractBinding<ActivityFeedBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    var onRefreshCallback: (() -> Unit)? = null
    val swipeProgress: ObservableField<Pair<Boolean?, Boolean?>> =
        ObservableField(Pair(first = true, second = false))
    var feedsAdapter: BindingRecyclerAdapter? = null

    override fun onCreated() {
        binding?.data = this
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        feedsAdapter = BindingRecyclerAdapter.Builder()
            .setLayoutResId(R.layout.item_feed_layout)
            .onBindViewHolderCallback { holder, _, adapter ->
                val feed = adapter.list[holder.adapterPosition] as? FeedInfoDto
                holder.binding.setVariable(BR.feed, feed)
            }
            .build()
        binding?.rvFeeds?.adapter = feedsAdapter
    }

    fun setRecyclerLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        binding?.rvFeeds?.layoutManager = layoutManager
    }

    fun showSwipeProgress(show: Boolean = true) {
        swipeProgress.set(Pair(first = true, second = show))
    }

    override fun onRefresh() {
        onRefreshCallback?.invoke()
    }

    override fun onDestroy() {
        binding?.data = null
    }
}