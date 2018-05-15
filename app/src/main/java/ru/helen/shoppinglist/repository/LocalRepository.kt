package ru.helen.shoppinglist.repository

import android.arch.lifecycle.LiveData
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.entity.ProductsInList
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Interface for repository localbd
 */
interface LocalRepository {
    fun getAllProduct(): LiveData<List<Product>>

    fun insertProduct(product: Product): Long

    fun deleteAllproducts()

    fun deleteOneProduct(productid: Int)

    fun updateProduct(name: String, productid: Int)

    fun getAllproductsInList(id: Long?): LiveData<List<ProductsInList>>

    fun insertProductsInList(relation: ProductsInList)

    fun deleteAllproductsInList()

    fun deleteOneProductFromList(productid: Int, listid: Int)

    fun checkProduct(check: Boolean, productid: Long, listid: Long)

    fun getAllshoppingList(): LiveData<List<Shoppinglist>>

    fun searchLists(namelist: String): LiveData<List<Shoppinglist>>

    fun insertShoppingList(list: Shoppinglist)

    fun deleteAllShopingList()

    fun deleteOneList(listid: Int)

    fun updateList(name: String, listid: Int)

    fun searchProduct(searchProduct: String): LiveData<List<Product>>

    fun checkProduct(searchProduct: String): LiveData<List<Product>>
}