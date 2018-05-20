package ru.helen.shoppinglist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.repository.LocalRepositoryImpl

class SearchProductModel(val repository: LocalRepositoryImpl): ViewModel() {
    fun searchProduct(name: String): LiveData<List<Product>> {
        return repository.searchProduct(name)
    }
}