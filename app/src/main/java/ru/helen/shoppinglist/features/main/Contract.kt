package ru.helen.shoppinglist.features.main

import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Created by lenap on 19.03.2018.
 */
interface Contract {
    interface ViewMain{
        fun updatemainlist(shoppinglists: List<Shoppinglist>)
        fun onItemClick(list: Shoppinglist)
    }

    interface Interactor{
        fun getAllShoppinglists(): List<Shoppinglist>
        fun addNewShoppingList(list: Shoppinglist)
    }

}