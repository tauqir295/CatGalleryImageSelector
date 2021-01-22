package com.gallery.cat.di

import android.content.Context
import androidx.databinding.library.BuildConfig
import com.gallery.cat.network.api.ApiHelper
import com.gallery.cat.network.api.ApiHelperImpl
import com.gallery.cat.network.api.ApiService
import com.gallery.cat.network.repository.MainRepository
import com.gallery.cat.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(ApplicationComponent::class)
@Module
object CatLibModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        } else OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    fun provideNetworkHelper(@ApplicationContext appContext: Context) = NetworkHelper(appContext)

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.thecatapi.com/") //base url for api calling
            .client(okHttpClient)
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideApiHelper(apiService: ApiService): ApiHelper = ApiHelperImpl(apiService)

    @Provides
    fun provideMainRepo(apiHelper: ApiHelper): MainRepository = MainRepository(apiHelper)
}