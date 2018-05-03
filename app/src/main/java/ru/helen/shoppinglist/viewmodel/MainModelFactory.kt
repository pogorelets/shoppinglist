package ru.helen.shoppinglist.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.helen.shoppinglist.repository.LocalRepositoryImpl




class MainModelFactory(val repository: LocalRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainModel::class.java)) {
            return MainModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}