package ru.helen.shoppinglist.ui.product

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_create_list.view.*
import kotlinx.android.synthetic.main.dialog_create_product.view.*
import ru.helen.shoppinglist.R

class DialogCreateProduct: DialogFragment() {
    interface ProductListener{
        fun insertProduct(nameProduct: String)
        fun searchProduct(search: String)
    }

    companion object {
        fun newInstance(): DialogCreateProduct {
            val result = DialogCreateProduct()
            return result
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val dialogView = activity.layoutInflater.inflate(R.layout.dialog_create_product, null)
        val nameList = dialogView.nameList

        val dialog = AlertDialog.Builder(activity, R.style.MyDialogTheme)
                .setView(dialogView)
                .setPositiveButton(activity.getString(R.string.create_list_button), { dialog, which ->
                    if (dialogView.nameProduct.text.toString()!= ""){
                        (activity as ProductListener).insertProduct(dialogView.nameProduct.text.toString())
                    }
                })

                .setNegativeButton(activity.getString(R.string.cancel_button), { _, _ -> })
                .create()

        return dialog
    }
}