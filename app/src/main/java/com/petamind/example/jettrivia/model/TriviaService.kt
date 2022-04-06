package com.petamind.example.jettrivia.model

import retrofit2.Response
import retrofit2.http.GET

interface TriviaService {
    @GET("/itmmckernan/triviajson/master/world.json")
    suspend fun getTriviaData(): Response<List<TriviaItem>>
}