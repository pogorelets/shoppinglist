package ru.helen.shoppinglist.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.helen.shoppinglist.entity.Product

/**
 * DAO for product
 */
@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT * FROM product where id = :listid")
    fun getProductByList(listid: Int): LiveData<List<Product>>

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Query("DELETE from product")
    fun deleteAll()

    @Query("DELETE from product where id = :productid")
    fun deleteOneProduct(productid: Int)

    @Query("UPDATE product set nameproduct = :name where id = :productid")
    fun updateProduct(name: String, productid: Int)
}