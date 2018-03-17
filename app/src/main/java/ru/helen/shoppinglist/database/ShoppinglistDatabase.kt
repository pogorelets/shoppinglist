package ru.helen.shoppinglist.database

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.helen.shoppinglist.dao.ProductDao
import ru.helen.shoppinglist.dao.ProductsInListDao
import ru.helen.shoppinglist.dao.ShoppinglistDao

/**
 * Room database for shoppinglist
 */
abstract class ShoppinglistDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao

    abstract fun shoppinglistDao(): ShoppinglistDao

    abstract fun productsInListDao(): ProductsInListDao

    companion object{
        private var INSTANCE: ShoppinglistDatabase? = null

        fun getInstance(context: Context):ShoppinglistDatabase? {
            if (INSTANCE == null) {
                synchronized(ShoppinglistDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShoppinglistDatabase::class.java, "shoppinglist.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}