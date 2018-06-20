
package ru.helen.shoppinglist.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.helen.shoppinglist.model.QuantProductInList
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
    fun insert(list: Shoppinglist): Long

    @Query("delete from shoppinglist")
    fun deleteAll()

    @Query("delete from shoppinglist where id = :listid")
    fun deleteOneList(listid: Long)

    @Query("update shoppinglist set namelist = :name where id = :listid")
    fun updateList(name: String, listid: Long)

    @Query("select s.id,s.namelist, s.datecreate, count(p.productid) as quantall, (select count(productsinlist.productid) from productsinlist where productsinlist.checking = 1 and productsinlist.listid = s.id ) as quantcheck  from shoppinglist as s left join productsinlist as p on s.id = p.listid group by  s.id,s.namelist, s.datecreate order by s.datecreate desc")
    fun getAllList(): LiveData<List<QuantProductInList>>

    @Query("select s.id,s.namelist, s.datecreate, count(p.productid) as quantall, (select count(productsinlist.productid) from productsinlist where productsinlist.checking = 1 and productsinlist.listid = s.id ) as quantcheck  from shoppinglist as s left join productsinlist as p on s.id = p.listid where s.namelist like '%' || :nameList  || '%'  group by  s.id,s.namelist, s.datecreate order by datecreate desc")
    fun searchList(nameList: String): LiveData<List<QuantProductInList>>



}