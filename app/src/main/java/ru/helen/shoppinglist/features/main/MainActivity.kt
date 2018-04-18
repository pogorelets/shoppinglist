package ru.helen.shoppinglist.features.main


import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Shoppinglist

class MainActivity : AppCompatActivity(), Contract.ViewMain {
    lateinit var adapter: MainAdapter
    //TODO dagger
    lateinit var presenter: Presenter

    fun createlist(){

        Log.e("CREATE", "CREATE")
    }

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

        presenter = Presenter(this, this)
        presenter.getShoppinglist()

        fun showDialog() {
            val newFragment = DialogCreateList.newInstance();
            newFragment.show(getFragmentManager(), "dialog");
        }



        fab.setOnClickListener { view -> showDialog()
        }
    }

    class DialogCreateList : DialogFragment() {

        companion object {
            fun newInstance(): DialogCreateList {
                val frag = DialogCreateList()
                return frag
            }
        }


        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            return AlertDialog.Builder(activity)
                    .setView(activity.layoutInflater.inflate(R.layout.dialog_create_list,null))
                    .setPositiveButton(getString(R.string.create_list_button), { dialog, which -> (activity as MainActivity).createlist() })
                    .setNegativeButton(getString(R.string.cancel_button), { dialog, which -> dismiss() })
                    .create()
        }
    }

}
