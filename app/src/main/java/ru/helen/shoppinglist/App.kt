package ru.helen.shoppinglist

import android.app.Application
import android.arch.persistence.room.Room
import ru.helen.shoppinglist.database.ShoppinglistDatabase

class App: Application() {
    companion object {
        var database: ShoppinglistDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        //App.database =  ShoppinglistDatabase.getInstance(applicationContext)//Room.databaseBuilder(this, ShoppinglistDatabase::class.java, "shoppinglist").build()
        App.database =  Room.databaseBuilder(this, ShoppinglistDatabase::class.java, "shoppinglist").build()
    }
}