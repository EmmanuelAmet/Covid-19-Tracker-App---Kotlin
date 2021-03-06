package com.emmanuelamet.covid19tracker.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emmanuelamet.covid19tracker.viewModel.MainViewModel
import com.emmanuelamet.covid19tracker.repository.Repository

class MainViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}