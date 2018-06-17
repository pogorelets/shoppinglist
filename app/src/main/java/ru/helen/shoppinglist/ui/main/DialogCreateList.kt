package ru.helen.shoppinglist.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.dialog_create_list.view.*
import ru.helen.shoppinglist.R

class DialogCreateList : DialogFragment() {


    companion object {
        fun newInstance(): DialogCreateList {
            val result = DialogCreateList()
            return result
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {

        val dialogView = activity.layoutInflater.inflate(R.layout.dialog_create_list, null)
        val nameList = dialogView.nameList

        val dialog = AlertDialog.Builder(activity, R.style.MyDialogTheme)
                .setView(dialogView)
                .setPositiveButton(activity.getString(R.string.create_list_button), { dialog, which ->
                    if (dialogView.nameList.text.toString()!= ""){
                        (activity as Contract.DialogCreateListener).insertList(dialogView.nameList.text.toString())
                    }
                    })

                .setNegativeButton(activity.getString(R.string.cancel_button), { _, _ -> })
                .create()

        // Добавляем затенение кнопки на случай, если в текстовом поле ничего не введено
        nameList.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        return dialog
    }

}