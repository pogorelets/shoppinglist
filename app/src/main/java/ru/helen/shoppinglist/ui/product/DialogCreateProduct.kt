package ru.helen.shoppinglist.ui.product

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.SearchView
import kotlinx.android.synthetic.main.dialog_create_product.view.*
import ru.helen.shoppinglist.App
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Product
import ru.helen.shoppinglist.viewmodel.ProductModel
import ru.helen.shoppinglist.viewmodel.ProductModelFactory
import javax.inject.Inject

class DialogCreateProduct: DialogFragment(), SearchProductAdapter.ClickSearchListener {
    lateinit var dialogView:  View/*= activity.layoutInflater.inflate(R.layout.dialog_create_product, null)*/

    @Inject
    lateinit var viewModelFactory: ProductModelFactory
    lateinit var viewModel: ProductModel

    override fun onSearchProductClick(item: Product) {
        dialogView.findViewById<SearchView>(R.id.searchProduct).setQuery(item.nameproduct, true)
    }

    interface ProductListener{
        fun checkProduct(nameProduct: String)
        fun searchProduct(search: String)
    }




    companion object {


        fun newInstance(): DialogCreateProduct {
            val result = DialogCreateProduct()
            return result
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        App.instance.appComponent.inject(this)
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(ProductModel::class.java!!)

        dialogView = activity.layoutInflater.inflate(R.layout.dialog_create_product, null)
        val adapter: SearchProductAdapter = SearchProductAdapter(this)
        dialogView.findViewById<RecyclerView>(R.id.searchProductList).layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        dialogView.findViewById<RecyclerView>(R.id.searchProductList).adapter = adapter
        dialogView.searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){

                    viewModel.searchProduct(newText).observe(activity, Observer { responce ->
                          adapter.swapData(responce!!)})
                }

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }



        })


        val dialog = AlertDialog.Builder(activity, R.style.MyDialogTheme)
                .setView(dialogView)
                .setPositiveButton(activity.getString(R.string.add_button), { dialog, which ->
                    if (dialogView.searchProduct.query!= ""){
                        Log.e("query", dialogView.searchProduct.query.toString())
                        (activity as ProductListener).checkProduct(dialogView.searchProduct.query.toString())
                    }
                })

                .setNegativeButton(activity.getString(R.string.cancel_button), { _, _ -> })
                .create()

        return dialog
    }
}