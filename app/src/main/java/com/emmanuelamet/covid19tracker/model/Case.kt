package com.emmanuelamet.covid19tracker.model

data class Case(
    val country:String,
    val countryInfo: countryInfo,
    val todayCases:String,
    val deaths:String,
    val todayDeaths:String,
    val recovered:String,
    val todayRecovered:String,
    val active:String,
    val critical:String,
    val casesPerOneMillion:String,
    val deathsPerOneMillion:String,
    val tests:String,
    val testsPerOneMillion:String,
    val population:String,
    val continent:String,
    val oneCasePerPeople:String,
    val oneDeathPerPeople:String,
    val oneTestPerPeople:String,
    val activePerOneMillion:String,
    val recoveredPerOneMillion:String,
    val criticalPerOneMillion:String
)
data class countryInfo(
    val flag:String
)