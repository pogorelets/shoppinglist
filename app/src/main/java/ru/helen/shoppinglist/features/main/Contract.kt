package ru.helen.shoppinglist.features.main

import io.reactivex.disposables.Disposable
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Created by lenap on 19.03.2018.
 */
interface Contract {
    interface OnGetList{
        fun onSuccessLoadedList(shoppingLists: List<Shoppinglist>)
        fun onErrorLoadedList(error: String)
    }

    interface ViewMain{
        fun updatemainlist(shoppinglists: List<Shoppinglist>)
        fun onItemClick(list: Shoppinglist)
    }

    interface Interactor{
        fun getAllShoppinglists(listener: OnGetList): Disposable
        fun addNewShoppingList(list: Shoppinglist)
    }

}