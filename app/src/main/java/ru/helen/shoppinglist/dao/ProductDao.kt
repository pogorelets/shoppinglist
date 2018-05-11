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
    @Query("select * from product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("select * from product where id = :listid")
    fun getProductByList(listid: Int): LiveData<List<Product>>

    @Insert(onConflict = REPLACE)
    fun insert(product: Product): Long

    @Query("delete from product")
    fun deleteAll()

    @Query("delete from product where id = :productid")
    fun deleteOneProduct(productid: Int)

    @Query("update product set nameproduct = :name where id = :productid")
    fun updateProduct(name: String, productid: Int)

    @Query("select * from product where nameproduct like '%' || :searchProduct  || '%'")
    fun searchProduct(searchProduct: String): LiveData<List<Product>>
}