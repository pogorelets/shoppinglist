package ru.helen.shoppinglist.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.helen.shoppinglist.model.ProductOutput
import ru.helen.shoppinglist.entity.ProductsInList

/**
 * DAO for productsinlist
 */
@Dao
interface ProductsInListDao {

    @Query("select p.productid as productId, p.listid as listId, p.checking, p1.nameproduct from productsinlist p join product p1 on p.productid = p1.id where listid = :id")
    fun getAll(id: Long?): LiveData<List<ProductOutput>>

    @Insert(onConflict = REPLACE)
    fun insert(relation: ProductsInList)

    @Query("delete from productsinlist")
    fun deleteAll()

    @Query("delete from productsinlist where productid = :productid and listid = :listid")
    fun deleteOneProduct(productid: Int, listid: Int)

    @Query("update productsinlist set checking = :check where productid = :productid and listid =:listid")
    fun checkProduct(check: Boolean, productid: Long, listid: Long)
}