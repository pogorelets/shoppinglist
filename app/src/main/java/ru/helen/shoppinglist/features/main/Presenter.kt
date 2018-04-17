package ru.helen.shoppinglist.features.main
import android.content.Context
import ru.helen.shoppinglist.App
import ru.helen.shoppinglist.database.ShoppinglistDatabase

/**
 * Created by lenap on 19.03.2018.
 */
class Presenter(val context: Context, val view: Contract.ViewMain) {
    //TODO позже уберём зависимости с помощью dagger
   // var db: ShoppinglistDatabase? = App.database
   // val interactor: InteractorImpl = InteractorImpl(db!!)

    fun getShoppinglist(){
       // view.updatemainlist(interactor.getAllShoppinglists())
    }

}