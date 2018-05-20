package ru.helen.shoppinglist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.model.ProductOutput
import ru.helen.shoppinglist.entity.ProductsInList
import ru.helen.shoppinglist.repository.LocalRepositoryImpl
import ru.helen.shoppinglist.repository.Storage

class ProductModel(val repository: LocalRepositoryImpl): ViewModel() {

    fun getAll(id: Long?): LiveData<List<ProductOutput>>{
        return  repository.getAllproductsInList(Storage.currentList.id)
    }

    fun insertProduct(name: String, listid: Long){
        val productid = repository.insertProduct(Product(null,name))
        repository.insertProductsInList(ProductsInList(productid, listid, false))

    }

    fun addProduct(id: Long,  listid: Long){
        repository.insertProductsInList(ProductsInList(id, listid, false))

    }

    fun chekProduct(name: String): LiveData<List<Product>>{
        return repository.checkProduct(name)
    }

    fun searchProduct(name: String): LiveData<List<Product>>{
        return repository.searchProduct(name)
    }

    fun setCheck(isChecked: Boolean, product: ProductOutput){
        repository.setCheckProduct(isChecked, product.productId, product.listId)
    }

    //TODO update
    //TODO delete
    //TODO deleteall

}