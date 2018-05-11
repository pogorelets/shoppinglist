package ru.helen.shoppinglist.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.helen.shoppinglist.repository.LocalRepositoryImpl

class ProductModelFactory(val repository: LocalRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductModel::class.java)) {
            return ProductModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}