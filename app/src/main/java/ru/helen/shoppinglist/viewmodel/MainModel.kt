package ru.helen.shoppinglist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.helen.shoppinglist.entity.Shoppinglist
import ru.helen.shoppinglist.repository.LocalRepositoryImpl

class MainModel(val repository: LocalRepositoryImpl): ViewModel() {

    fun getAll(): LiveData<List<Shoppinglist>>{
        return repository.getAllshoppingList()
    }

    fun searchList(namelist: String): LiveData<List<Shoppinglist>>{
        return repository.searchLists(namelist)
    }

    fun insert(list: Shoppinglist){
        repository.insertShoppingList(list)

    }

    fun deleteAll(){
        repository.deleteAllShopingList()
    }

    fun deleteOneList(listid: Int){
        repository.deleteOneList(listid)
    }

    fun updateList(name: String, listid: Int){
        repository.updateList(name,listid)
    }
}