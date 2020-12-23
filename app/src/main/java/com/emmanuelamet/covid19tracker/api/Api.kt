package com.emmanuelamet.covid19tracker.api

import com.emmanuelamet.covid19tracker.model.Case
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("v2/countries")
    suspend fun getAllCountries(): Response<List<Case>>

    @GET("v2/countries/{countryName}")
    suspend fun getCountry(@Path("countryName") country: String): Response<Case>
}