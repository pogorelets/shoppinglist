package ru.helen.shoppinglist.model

import java.util.*


data class QuantProductInList (
        var id: Long = 0,
        var namelist: String,
        var datecreate: Date,
        var quantall: Int,
        var quantcheck: Int
)
