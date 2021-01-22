package com.gallery.cat.network.api

import com.gallery.cat.network.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * retrofit service interface for defining the end points
 */
interface ApiService {
    @GET("/v1/images/search?")
    suspend fun getCatList(@Query("limit") limit : Int, @Query("page") page : Int): Response<List<Cat>>
}