package ru.helen.shoppinglist.repository

import android.arch.lifecycle.LiveData
import ru.helen.shoppinglist.database.ShoppinglistDatabase
import ru.helen.shoppinglist.entity.*
import ru.helen.shoppinglist.model.ProductOutput
import ru.helen.shoppinglist.model.QuantProductInList

/**
 * Local repository
 */
class LocalRepositoryImpl(val db: ShoppinglistDatabase ): LocalRepository {


    override fun copyList(products: List<ProductsInList>, newId: Long) {
        for (product in products){
            db.productsInListDao().insert(ProductsInList(product.productId,newId,false))
        }
    }

    override fun searchList(nameList: String): LiveData<List<QuantProductInList>> {
        return db.shoppinglistDao().searchList(nameList)
    }

    override fun checkProduct(searchProduct: String): LiveData<List<Product>> {
        return db.productDao().checkProduct(searchProduct)
    }

    override fun searchProduct(searchProduct: String): LiveData<List<Product>> {
        return db.productDao().searchProduct(searchProduct)
    }

    override fun searchLists(namelist: String): LiveData<List<Shoppinglist>> {
        return db.shoppinglistDao().searchLists(namelist)
    }

    override fun getAllProduct(): LiveData<List<Product>> {
        return db.productDao().getAllProduct()
    }

    override fun insertProduct(product: Product): Long {
        return db.productDao().insert(product)
    }

    override fun deleteAllproducts() {
        db.productDao().deleteAll()
    }

    override fun deleteOneProduct(productid: Int) {
        db.productDao().deleteOneProduct(productid)
    }

    override fun updateProduct(name: String, productid: Int) {
        db.productDao().updateProduct(name,productid)
    }

    override fun getAllproductsInList(id: Long?): LiveData<List<ProductOutput>> {
        return db.productsInListDao().getAll(id)
    }

    override fun insertProductsInList(relation: ProductsInList) {
        db.productsInListDao().insert(relation)
    }

    override fun deleteAllproductsInList() {
        db.productsInListDao().deleteAll()
    }

    override fun deleteOneProductFromList(productid: Int, listid: Int) {
        db.productsInListDao().deleteOneProduct(productid, listid)
    }

    override fun setCheckProduct(check: Boolean, productid: Long, listid: Long) {
        db.productsInListDao().checkProduct(check,productid,listid)
    }

    override fun getAllshoppingList(): LiveData<List<QuantProductInList>> {
        return db.shoppinglistDao().getAllList()
    }

    override fun insertShoppingList(list: Shoppinglist): Long {
        return db.shoppinglistDao().insert(list)
    }

    override fun deleteAllShopingList() {
        db.shoppinglistDao().deleteAll()
    }

    override fun deleteOneList(listid: Long) {
        db.productsInListDao().deleteProductsInList(listid)
        db.shoppinglistDao().deleteOneList(listid)

    }

    override fun updateList(name: String, listid: Long) {
        db.shoppinglistDao().updateList(name,listid)
    }

    override fun getProductsInList(id: Long): List<ProductsInList> {
        return db.productsInListDao().getProductsInList(id)
    }
}