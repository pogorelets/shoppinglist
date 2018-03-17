package ru.helen.shoppinglist.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Entity product
 */
@Entity(tableName = "product")
data class Product(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "nameproduct") var nameproduct: String
)  {

}