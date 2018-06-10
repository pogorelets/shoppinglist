package ru.helen.shoppinglist.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.helen.shoppinglist.App
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Shoppinglist
import ru.helen.shoppinglist.features.main.MainAdapter
import ru.helen.shoppinglist.viewmodel.MainModel

import java.util.*
import javax.inject.Inject
import android.support.v7.widget.SearchView
import ru.helen.shoppinglist.model.QuantProductInList

import ru.helen.shoppinglist.repository.Storage
import ru.helen.shoppinglist.ui.product.ProductActivity
import ru.helen.shoppinglist.viewmodel.ViewModelFactory


class MainActivity : AppCompatActivity(), DialogCreateList.DialogCreateListener, MainAdapter.ListClick, DialogWorkForList.ListEventsListener {
    lateinit var adapter: MainAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MainModel

    override fun onDeleteList(id: Long) {
        Log.e("DELETE", "DELETE")
    }

    override fun onRenameList(id: Long, newName: String) {
        Log.e("RENAME","RENAME")
    }

    override fun onCopyList(id: Long, name: String) {
        Log.e("COPY", "COPY")
    }

    override fun onShareList() {
        Log.e("SHARE", "SHARE")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        App.instance.appComponent.inject(this)
        adapter = MainAdapter(this)
        rvMainList.layoutManager = LinearLayoutManager(this)
        rvMainList.adapter = adapter
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainModel::class.java!!)

        viewModel.getAll().observe(this, Observer { responce -> showLists(responce) })

        fab.setOnClickListener {
            val dialog = DialogCreateList.newInstance()
            dialog.show(supportFragmentManager, "dialog")
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    searchList(newText)
                }

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }



        })

    }

    fun searchList(search: String){
        viewModel.searchList(search).observe(this, Observer { responce -> showLists(responce) })
    }


    override fun insertList(nameList: String) {
        Completable.fromAction(Action { viewModel.insert(Shoppinglist(null, nameList, Date())) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error -> Log.e("ERROR", error.toString()) })


    }

    fun showLists(lists: List<QuantProductInList>?) {
        if (lists != null) {
            adapter.swapData(lists)
        }
        Log.e("SIZE", lists?.size.toString())
    }

    override fun onListClick(list: QuantProductInList) {
        Storage.currentList = list
        startActivity(Intent(this, ProductActivity::class.java))
    }

    override fun onLongListClick(list: QuantProductInList) {
        val bottomSheetDialog = DialogWorkForList.newInstance(list.id,list.namelist)
        bottomSheetDialog.show(supportFragmentManager, "Custom Bottom Sheet")
    }


}