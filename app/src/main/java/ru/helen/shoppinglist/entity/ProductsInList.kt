package ru.helen.shoppinglist.entity

import android.arch.persistence.room.*

/**
 * Entity productsinlist
 */
@Entity(tableName = "productsinlist",
        primaryKeys = arrayOf("productid", "listid"),
        foreignKeys = arrayOf(ForeignKey(entity = Product::class, parentColumns = arrayOf("id"), childColumns = arrayOf("productid")),
                        ForeignKey(entity = Shoppinglist::class, parentColumns = arrayOf("id"), childColumns = arrayOf("listid"))))
data class ProductsInList(
      //  @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "productid") var productId: Int,
        @ColumnInfo(name = "listid") var listId: Int,
        @ColumnInfo(name = "checking") var check: Boolean
)




