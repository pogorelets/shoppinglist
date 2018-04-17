package ru.helen.shoppinglist.repository

import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.entity.ProductsInList
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Interface for repository localbd
 */
interface LocalRepository {
    fun getAllProduct(): List<Product>

    fun getProductByList(listid: Int): List<Product>

    fun insertProduct(product: Product)

    fun deleteAllproducts()

    fun deleteOneProduct(productid: Int)

    fun updateProduct(name: String, productid: Int)

    fun getAllproductsInList(): List<ProductsInList>

    fun insertProductsInList(relation: ProductsInList)

    fun deleteAllproductsInList()

    fun deleteOneProductFromList(productid: Int, listid: Int)

    fun checkProduct(check: Boolean, productid: Int, listid: Int)

    fun getAllshoppingList(): List<Shoppinglist>

    fun insertShoppingList(list: Shoppinglist)

    fun deleteAllShopingList()

    fun deleteOneList(listid: Int)

    fun updateList(name: String, listid: Int)
}