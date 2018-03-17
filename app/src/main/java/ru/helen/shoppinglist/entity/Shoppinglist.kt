package ru.helen.shoppinglist.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Entity shoppinglist
 */
@Entity(tableName = "shoppinglist")
data class Shoppinglist(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "namelist") var namelist: String,
        @ColumnInfo(name = "datecreate") var datecreate: Date
        )