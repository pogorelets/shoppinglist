package ru.helen.shoppinglist.ui
import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders

import android.os.Bundle

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.arch.lifecycle.Observer
import android.util.Log
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_create_list.view.*
import ru.helen.shoppinglist.App
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Shoppinglist
import ru.helen.shoppinglist.features.main.MainAdapter
import ru.helen.shoppinglist.viewmodel.MainModel
import ru.helen.shoppinglist.viewmodel.MainModelFactory
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MainAdapter
    @Inject
    lateinit var viewModelFactory: MainModelFactory
    lateinit var viewModel: MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        App.instance.appComponent.inject(this)
        adapter = MainAdapter()
        rvMainList.layoutManager = LinearLayoutManager(this)
        rvMainList.adapter = adapter

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainModel::class.java!!)

        viewModel.getAll().observe(this,Observer {responce -> showLists(responce) })
        fab.setOnClickListener { dialogInsert().show()}
    }

    fun dialogInsert(): Dialog {
        val view = this.layoutInflater.inflate(R.layout.dialog_create_list, null)
        return AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton(getString(R.string.create_list_button), { dialog, which ->insertList(view.nameList.text.toString())})
                .setNegativeButton(getString(R.string.cancel_button), { dialog, which -> dialog.dismiss() })
                .create()

    }

    fun insertList(nameList: String){
        Completable.fromAction(Action { viewModel.insert(Shoppinglist(null,nameList,Date())) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},{error -> Log.e("ERROR", error.toString())})


    }

    fun showLists(lists: List<Shoppinglist>?){
        if (lists != null) {
            adapter.swapData(lists)
        }
       Log.e("SIZE", lists?.size.toString())
    }

}
