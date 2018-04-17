package ru.helen.shoppinglist.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.helen.shoppinglist.entity.ProductsInList

/**
 * DAO for productsinlist
 */
@Dao
interface ProductsInListDao {
    @Query("SELECT * FROM productsinlist")
    fun getAll(): List<ProductsInList>

    @Insert(onConflict = REPLACE)
    fun insert(relation: ProductsInList)

    @Query("DELETE from productsinlist")
    fun deleteAll()

    @Query("DELETE from productsinlist where productid = :productid and listid = :listid")
    fun deleteOneProduct(productid: Int, listid: Int)

    @Query("UPDATE productsinlist set checking = :check where productid = :productid and listid =:listid")
    fun checkProduct(check: Boolean, productid: Int, listid: Int)
}