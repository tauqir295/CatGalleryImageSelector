package com.gallery.cat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gallery.cat.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity for entry point in cat library.
 */
@AndroidEntryPoint
class CatLibLandingActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_lib_landing)

        // navigate to landing fragment
        supportFragmentManager.beginTransaction().replace(
            R.id.catLibContainer,
            CatLibLandingFragment.newInstance()
        ).commitNow()
    }
}