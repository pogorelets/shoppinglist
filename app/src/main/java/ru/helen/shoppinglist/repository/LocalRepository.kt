package ru.helen.shoppinglist.repository

import android.arch.lifecycle.LiveData
import io.reactivex.Single
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.entity.ProductsInList
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Interface for repository localbd
 */
interface LocalRepository {
    fun getAllProduct(): LiveData<List<Product>>

    fun getProductByList(listid: Int): LiveData<List<Product>>

    fun insertProduct(product: Product)

    fun deleteAllproducts()

    fun deleteOneProduct(productid: Int)

    fun updateProduct(name: String, productid: Int)

    fun getAllproductsInList(): LiveData<List<ProductsInList>>

    fun insertProductsInList(relation: ProductsInList)

    fun deleteAllproductsInList()

    fun deleteOneProductFromList(productid: Int, listid: Int)

    fun checkProduct(check: Boolean, productid: Int, listid: Int)

    fun getAllshoppingList(): LiveData<List<Shoppinglist>>

    fun insertShoppingList(list: Shoppinglist)

    fun deleteAllShopingList()

    fun deleteOneList(listid: Int)

    fun updateList(name: String, listid: Int)
}