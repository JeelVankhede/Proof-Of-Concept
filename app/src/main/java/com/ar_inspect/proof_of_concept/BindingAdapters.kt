package com.ar_inspect.proof_of_concept

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.awesome_lib.core.gone
import com.awesome_lib.core.show
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * [BindingAdapters] :
 *
 * Object holds various methods for Data-binding related `BindingAdapters` of custom attributes
 * with custom logic implementation.
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 10/16/2019
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter(
        "imageUrl",
        requireAll = false
    )
    fun loadServerImage(
        imageView: ImageView?,
        imageUrl: String?
    ) {
        if (!imageUrl.isNullOrEmpty()) {
            imageView?.let { iv ->
                Glide.with(iv.context)
                    .load(imageUrl)
                    .apply(RequestOptions().override(iv.width, iv.height))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv)
                iv.show()
            }
        } else {
            imageView?.gone()
        }

    }

    @JvmStatic
    @BindingAdapter("refreshing", requireAll = false)
    fun setSwipeRefreshing(swipeRefreshLayout: SwipeRefreshLayout?, isRefreshing: Boolean) {
        swipeRefreshLayout?.isRefreshing = isRefreshing
    }

    @JvmStatic
    @BindingAdapter("setVisibility", requireAll = false)
    fun setVisibility(view: View?, show: Boolean) {
        if (show) view?.show() else view?.gone()
    }
}