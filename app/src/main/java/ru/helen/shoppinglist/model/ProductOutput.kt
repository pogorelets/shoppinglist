package ru.helen.shoppinglist.model

data class ProductOutput (
            var productId: Long,
            var listId: Long,
            var checking: Boolean,
            var nameproduct: String
)
