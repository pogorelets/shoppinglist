package ru.helen.shoppinglist.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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


class MainActivity : AppCompatActivity(), Contract.DialogCreateListener, Contract.ListClick, Contract.ListEventsListener, Contract.DeleteListener, Contract.EditListener {


    lateinit var adapter: MainAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MainModel
    lateinit var bottomSheetDialog: DialogWorkForList

    override fun onConfirmDeleteList(id: Long) {
        bottomSheetDialog.dismiss()
        val confirmDeleteDialog = DialogConfirmDelete.newInstance(id)
        confirmDeleteDialog.show(supportFragmentManager, "Confirm Delete")

    }

    override fun onDeleteListener(id: Long) {
        viewModel.deleteOneList(id)
    }

    override fun onConfirmRenameList(id: Long, oldName: String) {
        bottomSheetDialog.dismiss()
        val confirmRenameDialog = DialogRenameOrCopyList.newInstance(id,oldName,"edit")
        confirmRenameDialog.show(supportFragmentManager, "Confirm Rename")
    }

    override fun onConfirmCopyList(id: Long, name: String) {
        bottomSheetDialog.dismiss()
        val confirmCopyDialog = DialogRenameOrCopyList.newInstance(id, name,"copy")
        confirmCopyDialog.show(supportFragmentManager,"Confirm copy")
    }

    override fun onShareList(id: Long, name: String) {
        //TODO make file
        //функция, которая по id выбирает все продукты и все продукты в списке, учитывая галочки
        //собираем файл будет начинаться со слов список покупок потом имя файла
        //с новой строки каждую покупку
        //в конце каждой строки да/нет(куплено /не куплено), чтобы можно было делиться
        //даже уже начатым списком
        //TODO share file
        //делимся через мессенжеры, которые могут отправить файл

    }

    override fun onRenameList(id: Long, name: String) {
        viewModel.updateList(name,id)
    }

    override fun onCopyList(id: Long, name: String) {
        viewModel.copyList(Shoppinglist(null,name,Date()),id)
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
        viewModel.insert(Shoppinglist(null, nameList, Date()))
    }

    fun showLists(lists: List<QuantProductInList>?) {
        if (lists != null) {
            adapter.swapData(lists)
        }
    }

    override fun onListClick(list: QuantProductInList) {
        Storage.currentList = list
        startActivity(Intent(this, ProductActivity::class.java))
    }

    override fun onLongListClick(list: QuantProductInList) {
        bottomSheetDialog = DialogWorkForList.newInstance(list.id,list.namelist)
        bottomSheetDialog.show(supportFragmentManager, "Custom Bottom Sheet")
    }


}