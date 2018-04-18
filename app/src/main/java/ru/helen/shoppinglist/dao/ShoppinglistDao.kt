package ru.helen.shoppinglist.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Single
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * DAO for shoppinglist
 */
@Dao
interface ShoppinglistDao {
    @Query("SELECT * FROM shoppinglist")
    fun getAll(): Single<List<Shoppinglist>>

    @Insert(onConflict = REPLACE)
    fun insert(list: Shoppinglist)

    @Query("DELETE from shoppinglist")
    fun deleteAll()

    @Query("DELETE from shoppinglist where id = :listid")
    fun deleteOneList(listid: Int)

    @Query("UPDATE shoppinglist set namelist = :name where id = :listid")
    fun updateList(name: String, listid: Int)
}