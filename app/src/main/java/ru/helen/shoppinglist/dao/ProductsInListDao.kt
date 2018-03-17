package ru.helen.shoppinglist.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.helen.shoppinglist.entity.ProductsInList

/**
 * DAO for productsinlist
 */
interface ProductsInListDao {
    @Query("SELECT * FROM productsinlist")
    fun getAll(): List<ProductsInList>

    @Insert(onConflict = REPLACE)
    fun insert(relation: ProductsInList)

    @Query("DELETE from productsinlist")
    fun deleteAll()

    @Query("DELETE from productsinlist where id = :productid")
    fun deleteOneProduct(productid: Int)

    @Query("UPDATE productsinlist set check = :check where productid = :productid and listid =:listid")
    fun checkProduct(check: Boolean, productid: Int, listid: Int)
}