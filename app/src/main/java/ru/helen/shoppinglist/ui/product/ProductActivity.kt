package ru.helen.shoppinglist.ui.product

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.content_product.*
import ru.helen.shoppinglist.App
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.model.ProductOutput
import ru.helen.shoppinglist.repository.Storage
import ru.helen.shoppinglist.viewmodel.ProductModel
import ru.helen.shoppinglist.viewmodel.ViewModelFactory
import javax.inject.Inject

class ProductActivity : AppCompatActivity(), DialogCreateProduct.ProductListener, ProductAdapter.CheckListener, SearchProductAdapter.ClickSearchListener {

    lateinit var adapter: ProductAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ProductModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        App.instance.appComponent.inject(this)
        adapter = ProductAdapter(this)
        rvProductList.layoutManager = LinearLayoutManager(this)
        rvProductList.adapter = adapter

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(ProductModel::class.java!!)
        viewModel.getAll(Storage.currentList.id).observe(this, Observer { responce -> showProducts(responce) })

        fab.setOnClickListener {
            val dialog = DialogCreateProduct.newInstance()
            dialog.show(supportFragmentManager, "dialog")
        }
    }

    fun showProducts(products: List<ProductOutput>?) {
        if (products != null) {
            adapter.swapData(products)
        }
    }

    override fun checkProduct(nameProduct: String) {
        viewModel.chekProduct(nameProduct).observe(this, Observer { responce ->
            if (responce?.size == 0) {
                insertProduct(nameProduct)
            } else {
                addProduct(responce?.get(0)?.id,nameProduct)
            }
        })


    }

    fun addProduct(id: Long?, nameProduct: String){
        Completable.fromAction(Action {
            if (id != null) {
                viewModel.addProduct(id, nameProduct, Storage.currentList.id!!)
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", error.toString()) })

    }

    fun insertProduct(nameProduct: String) {
        Completable.fromAction(Action { viewModel.insertProduct(nameProduct, Storage.currentList.id!!) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", error.toString()) })
    }

    override fun searchProduct(search: String) {
        viewModel.searchProduct(search).observe(this, Observer { responce -> })
    }

    override fun onChangeCheck(isChecked: Boolean, product: ProductOutput) {
        Completable.fromAction(Action { viewModel.setCheck(isChecked, product) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", error.toString()) })

    }

    override fun onSearchProductClick(item: Product) {

    }


}