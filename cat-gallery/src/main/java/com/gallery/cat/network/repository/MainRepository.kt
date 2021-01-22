package com.gallery.cat.network.repository

import com.gallery.cat.network.api.ApiHelper

/**
 * Repository class used for managing different api calls
 */
class MainRepository (private val apiHelper: ApiHelper) {
    suspend fun getCats(limit:Int, page:Int) =  apiHelper.getCats(limit, page)
}