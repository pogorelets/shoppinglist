package ru.helen.shoppinglist.features.main

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.helen.shoppinglist.database.ShoppinglistDatabase
import ru.helen.shoppinglist.entity.Shoppinglist
import ru.helen.shoppinglist.repository.LocalRepositoryImpl

/**
 * Created by lenap on 19.03.2018.
 */
class InteractorImpl(val db: ShoppinglistDatabase): Contract.Interactor {
    val repository: LocalRepositoryImpl = LocalRepositoryImpl(db)

    override fun getAllShoppinglists(listener: Contract.OnGetList): Disposable   {
        return repository.getAllshoppingList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({lists -> listener.onSuccessLoadedList(lists) })
    }


    override fun addNewShoppingList(list: Shoppinglist) {
        repository.insertShoppingList(list)
    }
}