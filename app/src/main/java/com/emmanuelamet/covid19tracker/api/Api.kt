package com.emmanuelamet.covid19tracker.api

import com.emmanuelamet.covid19tracker.model.Case
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("v2/countries")
    suspend fun getAllCountries(): Response<List<Case>>
}