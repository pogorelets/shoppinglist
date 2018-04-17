package ru.helen.shoppinglist.features.main

import ru.helen.shoppinglist.database.ShoppinglistDatabase
import ru.helen.shoppinglist.entity.Shoppinglist
import ru.helen.shoppinglist.repository.LocalRepositoryImpl

/**
 * Created by lenap on 19.03.2018.
 */
class InteractorImpl(val db: ShoppinglistDatabase): Contract.Interactor {
    val repository: LocalRepositoryImpl = LocalRepositoryImpl(db)

    override fun getAllShoppinglists(): List<Shoppinglist> {
        return repository.getAllshoppingList()
    }

    override fun addNewShoppingList(list: Shoppinglist) {
        repository.insertShoppingList(list)
    }
}