package ru.helen.shoppinglist.features.main


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Shoppinglist

class MainActivity : AppCompatActivity(), Contract.ViewMain {
    lateinit var adapter: MainAdapter
    //TODO dagger
    lateinit var presenter: Presenter


    override fun updatemainlist(shoppinglists: List<Shoppinglist>) {
        adapter.swapData(shoppinglists)
    }

    override fun onItemClick(list: Shoppinglist) {
        Log.e("PUFF", "Переход в список")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapter = MainAdapter(this)
        rvMainList.layoutManager = LinearLayoutManager(this)
        rvMainList.adapter = adapter

        presenter = Presenter(this,this)
        presenter.getShoppinglist()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
