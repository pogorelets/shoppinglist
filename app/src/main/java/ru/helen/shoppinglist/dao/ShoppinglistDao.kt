
package ru.helen.shoppinglist.dao

import android.arch.lifecycle.LiveData
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
    @Query("select * from shoppinglist order by datecreate desc")
    fun getAll(): LiveData<List<Shoppinglist>>

    @Query("select * from shoppinglist where namelist like '%' || :namelist  || '%' order by datecreate desc ")
    fun searchLists(namelist: String): LiveData<List<Shoppinglist>>

    @Insert(onConflict = REPLACE)
    fun insert(list: Shoppinglist)

    @Query("delete from shoppinglist")
    fun deleteAll()

    @Query("delete from shoppinglist where id = :listid")
    fun deleteOneList(listid: Int)

    @Query("update shoppinglist set namelist = :name where id = :listid")
    fun updateList(name: String, listid: Int)
}