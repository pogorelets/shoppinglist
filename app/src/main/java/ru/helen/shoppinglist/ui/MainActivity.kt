package ru.helen.shoppinglist.features.main


import android.app.Dialog
import android.app.DialogFragment

import android.os.Bundle

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log



import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Shoppinglist

class MainActivity : AppCompatActivity(){
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



//        fun showDialog() {
//            val newFragment = DialogCreateList.newInstance();
//            newFragment.show(getFragmentManager(), "dialog");
//        }



        fab.setOnClickListener {         }
    }

//    class DialogCreateList : DialogFragment() {
//
//        companion object {
//            fun newInstance(): DialogCreateList {
//                val frag = DialogCreateList()
//                return frag
//            }
//        }
//
//
//        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//
//            return AlertDialog.Builder(activity)
//                    .setView(activity.layoutInflater.inflate(R.layout.dialog_create_list,null))
//                    .setPositiveButton(getString(R.string.create_list_button), { dialog, which ->  })
//                    .setNegativeButton(getString(R.string.cancel_button), { dialog, which -> dismiss() })
//                    .create()
//        }
//    }

}
