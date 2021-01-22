package com.gallery.cat.network.model

import android.os.Parcelable
import com.gallery.cat.network.model.Breed
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
): Parcelable