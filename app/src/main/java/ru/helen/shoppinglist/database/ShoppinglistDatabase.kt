package ru.helen.shoppinglist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import ru.helen.shoppinglist.dao.ProductDao
import ru.helen.shoppinglist.dao.ProductsInListDao
import ru.helen.shoppinglist.dao.ShoppinglistDao
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.entity.ProductsInList
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Room database for shoppinglist
 */
@Database(entities = [(Product::class), (ProductsInList::class), (Shoppinglist::class)], version = 1)
@TypeConverters(Converters::class)
abstract class ShoppinglistDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao

    abstract fun shoppinglistDao(): ShoppinglistDao

    abstract fun productsInListDao(): ProductsInListDao


}