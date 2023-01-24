package com.example.hypergol.di

import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.util.Constants.API_BASE_DEV
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        val json = Json {
            // Ignore fields not present in the model
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(API_BASE_DEV)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideLaunchApi(retrofit: Retrofit): LaunchApi {
        return retrofit.create(LaunchApi::class.java)
    }

}