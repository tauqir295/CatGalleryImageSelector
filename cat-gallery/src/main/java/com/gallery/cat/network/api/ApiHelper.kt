package com.gallery.cat.network.api

import com.gallery.cat.network.model.Cat
import retrofit2.Response

/**
 * Interface for API class
 */
interface ApiHelper {
    suspend fun getCats(limit:Int, page:Int): Response<List<Cat>>
}