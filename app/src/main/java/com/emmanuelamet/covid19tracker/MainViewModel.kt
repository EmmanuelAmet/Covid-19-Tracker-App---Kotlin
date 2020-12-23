package com.emmanuelamet.covid19tracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanuelamet.covid19tracker.model.Case
import com.emmanuelamet.covid19tracker.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse : MutableLiveData<Response<List<Case>>> = MutableLiveData()
    val myCountryResponse : MutableLiveData<Response<Case>> = MutableLiveData()

    fun getAllCases(){
        viewModelScope.launch {
            val response: Response<List<Case>> = repository.getAllCountries()
            myResponse.value = response
        }
    }

    fun getCountry(country:String){
        viewModelScope.launch {
            val response : Response<Case> = repository.getCountry(country)
            myCountryResponse.value = response
        }
    }

}