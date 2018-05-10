package ru.helen.shoppinglist.ui


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
import ru.helen.shoppinglist.viewmodel.MainModelFactory
import java.util.*
import javax.inject.Inject
import android.support.v7.widget.SearchView
import ru.helen.shoppinglist.repository.Storage


class MainActivity : AppCompatActivity(), DialogCreateList.DialogCreateListener, MainAdapter.ListClick {

    lateinit var adapter: MainAdapter
    @Inject
    lateinit var viewModelFactory: MainModelFactory
    lateinit var viewModel: MainModel

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

    fun showLists(lists: List<Shoppinglist>?) {
        if (lists != null) {
            adapter.swapData(lists)
        }
        Log.e("SIZE", lists?.size.toString())
    }

    override fun onListClick(list: Shoppinglist) {
        Storage.currentList = list
        startActivity(Intent(this, ProductActivity::class.java))
    }


}
