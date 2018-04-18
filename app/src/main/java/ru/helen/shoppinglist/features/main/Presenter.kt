package ru.helen.shoppinglist.features.main
import android.content.Context
import android.util.Log
import ru.helen.shoppinglist.App
import ru.helen.shoppinglist.database.ShoppinglistDatabase
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Created by lenap on 19.03.2018.
 */
class Presenter(val context: Context, val view: Contract.ViewMain): Contract.OnGetList {

    //TODO позже уберём зависимости с помощью dagger

    val interactor: InteractorImpl = InteractorImpl(App.database!!)

    fun getShoppinglist(){
        interactor.getAllShoppinglists(this)
    }

    override fun onSuccessLoadedList(shoppingLists: List<Shoppinglist>) {
        view.updatemainlist(shoppingLists)
    }

    override fun onErrorLoadedList(error: String) {
        Log.e("ERROR",error)
    }

}