package com.petamind.example.jettrivia.model

import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TriviaAPI {
    @GET("/itmmckernan/triviajson/master/world.json")
    suspend fun getTriviaData(): Response<List<TriviaItem>>
}