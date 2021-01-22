package com.gallery.cat.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallery.cat.network.model.Cat
import com.gallery.cat.network.repository.MainRepository
import com.gallery.cat.utils.NetworkHelper
import com.gallery.cat.utils.Resource
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * View model class used for updating the UI
 */
class CatLibLandingViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _cats = MutableLiveData<Resource<List<Cat>>>()
    val catList: LiveData<Resource<List<Cat>>>
        get() = _cats

    init {
        fetchCatList()
    }

    /**
     * Fetch the data from API
     * @param - [limit] - variable used for getting number of records at first
     * @param - [page] - variable used for getting number of pages
     */
    fun fetchCatList(limit: Int = 18, page: Int = 0) {
        viewModelScope.launch {
            _cats.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getCats(limit, page).let {
                        if (it.isSuccessful) {
                            _cats.postValue(Resource.success(it.body()))
                        } else _cats.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } catch (e: IOException) {
                    _cats.postValue(Resource.error("No internet connection", null))
                }

            } else _cats.postValue(Resource.error("No internet connection", null))
        }
    }
}