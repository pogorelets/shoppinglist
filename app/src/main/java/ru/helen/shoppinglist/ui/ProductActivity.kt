package ru.helen.shoppinglist.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product.*
import ru.helen.shoppinglist.R

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        fab.setOnClickListener {
            val dialog = DialogCreateProduct.newInstance()
            dialog.show(supportFragmentManager, "dialog")
        }

    }
}
