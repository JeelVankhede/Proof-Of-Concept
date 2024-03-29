package com.ar_inspect.proof_of_concept.api

import com.ar_inspect.proof_of_concept.feed.model.FeedListDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * [FeedsApi] :
 *
 * Feeds API interface provides method for Retrofit endpoint connection as RESTFul APIs
 *
 * @author : Jeel Vankhede
 * @version 1.0.0
 * @since 10/15/2019
 */

interface FeedsApi {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFeedsDetails(): Call<FeedListDto?>
}