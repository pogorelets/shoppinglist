package ru.helen.shoppinglist.repository

import android.arch.lifecycle.LiveData
import ru.helen.shoppinglist.entity.*
import ru.helen.shoppinglist.model.ProductOutput
import ru.helen.shoppinglist.model.QuantProductInList

/**
 * Interface for repository localbd
 */
interface LocalRepository {
    fun getAllProduct(): LiveData<List<Product>>

    fun insertProduct(product: Product): Long

    fun deleteAllproducts()

    fun deleteOneProduct(productid: Int)

    fun updateProduct(name: String, productid: Int)

    fun getAllproductsInList(id: Long?): LiveData<List<ProductOutput>>

    fun getProductsInList(id: Long): List<ProductsInList>

    fun insertProductsInList(relation: ProductsInList)

    fun deleteAllproductsInList()

    fun deleteOneProductFromList(productid: Int, listid: Int)

    fun setCheckProduct(check: Boolean, productid: Long, listid: Long)

    fun getAllshoppingList(): LiveData<List<QuantProductInList>>

    fun searchLists(namelist: String): LiveData<List<Shoppinglist>>

    fun insertShoppingList(list: Shoppinglist): Long

    fun deleteAllShopingList()

    fun deleteOneList(listid: Long)

    fun updateList(name: String, listid: Long)

    fun searchProduct(searchProduct: String): LiveData<List<Product>>

    fun checkProduct(searchProduct: String): LiveData<List<Product>>

    fun searchList(nameList: String): LiveData<List<QuantProductInList>>

    fun copyList(products: List<ProductsInList>, newId: Long)
}

