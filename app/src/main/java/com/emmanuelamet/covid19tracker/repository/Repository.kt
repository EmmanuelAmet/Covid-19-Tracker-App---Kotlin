package com.emmanuelamet.covid19tracker.repository

import com.emmanuelamet.covid19tracker.api.ApiInstance
import com.emmanuelamet.covid19tracker.model.Case
import retrofit2.Response

class Repository {
    suspend fun getAllCountries(): Response<List<Case>>{
        return ApiInstance.api.getAllCountries()
    }
}