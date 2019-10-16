package com.ar_inspect.proof_of_concept.feed.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ar_inspect.proof_of_concept.R
import com.awesome_lib.core.AbstractBaseActivity
import com.awesome_lib.core.EventObserver
import com.awesome_lib.core.absBuilder
import com.awesome_lib.core.api.takeIfError
import com.awesome_lib.core.api.takeIfErrorMessage
import com.awesome_lib.core.api.takeIfNoConnection
import com.awesome_lib.core.isNetworkConnected
import com.google.android.material.snackbar.Snackbar

/**
 * [FeedsActivity] :
 *
 * @author : Jeel Vankhede
 * @version 1.0.0
 * @since 10/16/2019
 */

class FeedsActivity : AbstractBaseActivity() {
    companion object {
        const val EXTRA_FLAG_CALL_API = "makeApiCall"
    }

    private val activityMainBinder by lazy {
        return@lazy FeedsActivityBinder()
    }

    private val feedsDetailViewModel by lazy {
        ViewModelProviders.of(this)[FeedsViewModel::class.java]
    }

    override fun setUpBuilder() = absBuilder {
        contentView = R.layout.activity_feed
        abstractBinding = activityMainBinder
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        title = getString(R.string.app_name)
        setupRefreshCallbacks()
        observeData()
        if (savedInstanceState == null) {
            getFeeds()
        } else {
            if (savedInstanceState.keySet().contains(EXTRA_FLAG_CALL_API)
                && savedInstanceState.getBoolean(EXTRA_FLAG_CALL_API, false)
            )
                getFeeds()
        }
    }

    private fun setupRefreshCallbacks() {
        activityMainBinder.onRefreshCallback = {
            if (isNetworkConnected())
                feedsDetailViewModel.refreshFeeds()
            else
                rootView.showSnackBar(getString(R.string.no_internet))
        }
    }

    private fun getFeeds() {
        feedsDetailViewModel.getFeeds()
    }

    private fun observeData() {
        feedsDetailViewModel.feedsLiveData.observe(this, Observer {
            activityMainBinder.showSwipeProgress(false)
            when (resources?.configuration?.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    activityMainBinder.setRecyclerLayoutManager(
                        StaggeredGridLayoutManager(
                            2,
                            StaggeredGridLayoutManager.VERTICAL
                        )
                    )
                }
                else -> {
                    activityMainBinder.setRecyclerLayoutManager(
                        LinearLayoutManager(
                            this,
                            RecyclerView.VERTICAL, false
                        )
                    )
                }
            }
            title = feedsDetailViewModel.getPageTitle()
            activityMainBinder.feedsAdapter?.addAllItems(it)
        })
        feedsDetailViewModel.errorLiveData.observe(this, EventObserver {
            activityMainBinder.showSwipeProgress(false)
            title = getString(R.string.failed_to_load)
            it takeIfError {
                activityMainBinder.feedsAdapter?.clear()
                tr.message?.let { msg -> rootView.showSnackBar(msg) }
            } takeIfNoConnection {
                activityMainBinder.feedsAdapter?.clear()
                rootView.showSnackBar(getString(R.string.no_internet))
            } takeIfErrorMessage {
                activityMainBinder.feedsAdapter?.clear()
                rootView.showSnackBar(getErrorMessage())
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(EXTRA_FLAG_CALL_API, false)
        super.onSaveInstanceState(outState)
    }

    private fun View?.showSnackBar(message: String) {
        val snackBar = this?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG) }
        snackBar?.show()
    }
}
