package com.gallery.cat.network.api

import com.gallery.cat.network.model.Cat
import retrofit2.Response

/**
 * Interface implementation for ApiHelper
 * @param - [ApiService] - class is used for fetching data from server
 */
class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCats(limit: Int, page: Int): Response<List<Cat>> =
        apiService.getCatList(limit,page)
}