package ru.helen.shoppinglist.features.main
import android.app.Dialog

import android.os.Bundle

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.viewmodel.MainModel
import ru.helen.shoppinglist.viewmodel.MainModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var adapter: MainAdapter
    private lateinit var viewModelFactory: MainModelFactory
    private lateinit var viewModel: MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        adapter = MainAdapter()
        rvMainList.layoutManager = LinearLayoutManager(this)
        rvMainList.adapter = adapter


        fab.setOnClickListener { dialogInsert().show()}
    }

    fun dialogInsert(): Dialog {
        return AlertDialog.Builder(this)
                .setView(this.layoutInflater.inflate(R.layout.dialog_create_list, null))
                .setPositiveButton(getString(R.string.create_list_button), { dialog, which -> })
                .setNegativeButton(getString(R.string.cancel_button), { dialog, which -> dialog.dismiss() })
                .create()

    }

}
