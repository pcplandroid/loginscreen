package com.example.pagingsample.api

import com.example.pagingsample.modal.Player
import com.example.pagingsample.modal.Players
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {

    @GET("players")
    suspend fun getPlayersList(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Players
}