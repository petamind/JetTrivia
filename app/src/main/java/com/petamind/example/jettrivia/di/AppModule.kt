package com.petamind.example.jettrivia.di

import com.petamind.example.jettrivia.model.TriviaAPI
import com.petamind.example.jettrivia.util.QUIZ_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideTriviaAPI(): TriviaAPI {
        return Retrofit.Builder().baseUrl(QUIZ_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()))
            .build().create(TriviaAPI::class.java)
    }
}