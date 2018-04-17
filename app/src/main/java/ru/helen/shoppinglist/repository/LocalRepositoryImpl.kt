package ru.helen.shoppinglist.repository

import ru.helen.shoppinglist.database.ShoppinglistDatabase
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.entity.ProductsInList
import ru.helen.shoppinglist.entity.Shoppinglist

/**
 * Local repository
 */
class LocalRepositoryImpl(val db: ShoppinglistDatabase ): LocalRepository {
    override fun getAllProduct(): List<Product> {
        return db?.productDao()?.getAllProduct()
    }

    override fun getProductByList(listid: Int): List<Product> {
        return db?.productDao()?.getProductByList(listid)
    }

    override fun insertProduct(product: Product) {
        db?.productDao()?.insert(product)
    }

    override fun deleteAllproducts() {
        db?.productDao()?.deleteAll()
    }

    override fun deleteOneProduct(productid: Int) {
        db?.productDao()?.deleteOneProduct(productid)
    }

    override fun updateProduct(name: String, productid: Int) {
        db?.productDao()?.updateProduct(name,productid)
    }

    override fun getAllproductsInList(): List<ProductsInList> {
        return db?.productsInListDao().getAll()
    }

    override fun insertProductsInList(relation: ProductsInList) {
        db?.productsInListDao().insert(relation)
    }

    override fun deleteAllproductsInList() {
        db?.productsInListDao().deleteAll()
    }

    override fun deleteOneProductFromList(productid: Int, listid: Int) {
        db?.productsInListDao().deleteOneProduct(productid, listid)
    }

    override fun checkProduct(check: Boolean, productid: Int, listid: Int) {
        db?.productsInListDao().checkProduct(check,productid,listid)
    }

    override fun getAllshoppingList(): List<Shoppinglist> {
        return db?.shoppinglistDao().getAll()
    }

    override fun insertShoppingList(list: Shoppinglist) {
        db?.shoppinglistDao().insert(list)
    }

    override fun deleteAllShopingList() {
        db?.shoppinglistDao().deleteAll()
    }

    override fun deleteOneList(listid: Int) {
        db?.shoppinglistDao().deleteOneList(listid)
    }

    override fun updateList(name: String, listid: Int) {
        db?.shoppinglistDao().updateList(name,listid)
    }
}