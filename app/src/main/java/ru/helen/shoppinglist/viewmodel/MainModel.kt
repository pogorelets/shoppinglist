package ru.helen.shoppinglist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.helen.shoppinglist.model.QuantProductInList
import ru.helen.shoppinglist.entity.Shoppinglist
import ru.helen.shoppinglist.repository.LocalRepositoryImpl
import io.reactivex.functions.Action

class MainModel(val repository: LocalRepositoryImpl) : ViewModel() {
    fun getAll(): LiveData<List<QuantProductInList>> {
        return repository.getAllshoppingList()
    }

    fun searchList(namelist: String): LiveData<List<QuantProductInList>> {
        return repository.searchList(namelist)
    }

    fun insert(list: Shoppinglist) {
        Completable.fromAction(Action { repository.insertShoppingList(list) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", error.toString()) })
    }

    fun copyList(list: Shoppinglist, oldId: Long) {
        Completable.fromAction(Action {
            val newId: Long = repository.insertShoppingList(list)
            val prodInList = repository.getProductsInList(oldId)
            repository.copyList(prodInList, newId)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", error.toString()) })
    }

    fun deleteAll() {
        repository.deleteAllShopingList()
    }

    fun deleteOneList(listid: Long) {
        Completable.fromAction(Action { repository.deleteOneList(listid) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", "Ошибка при удалении списка $error") })
    }

    fun updateList(name: String, listid: Long) {
        Completable.fromAction(Action { repository.updateList(name, listid) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", "Ошибка при переименовании списка $error") })

    }
}