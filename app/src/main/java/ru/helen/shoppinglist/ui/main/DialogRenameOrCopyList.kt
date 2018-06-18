package ru.helen.shoppinglist.ui.main

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_rename_copy_list.view.*
import ru.helen.shoppinglist.R

class DialogRenameOrCopyList : BottomSheetDialogFragment() {
    private lateinit var listener: Contract.EditListener

    companion object {
        private const val ID = "id"
        private const val NAME_LIST = "namelist"
        private const val FLAG = "flag"
        fun newInstance(id: Long, nameList: String, flag: String): DialogRenameOrCopyList {
            val result = DialogRenameOrCopyList()
            val bundle = Bundle()
            bundle.putLong(ID, id)
            bundle.putString(NAME_LIST, nameList)
            bundle.putString(FLAG, flag)
            result.arguments = bundle
            return result
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.dialog_rename_copy_list, container, false)
        listener = activity as Contract.EditListener

        val args = arguments

        when (args.getString(FLAG)){
            "copy" -> {
                val text = "${args.getString(NAME_LIST)} копия" //TODO в ресурсы
                view.nameList.setText(text)
            }
            else -> view.nameList.setText(args.getString(NAME_LIST))
        }
        view.btnCancel.setOnClickListener {
            dismiss()
        }

        view.btnSave.setOnClickListener {
            if (view.nameList.toString() != "") {
                when (args.getString(FLAG)) {
                    "copy" -> listener.onCopyList(args.getLong(ID), view.nameList.text.toString())
                    else -> listener.onRenameList(args.getLong(ID), view.nameList.text.toString())
                }

                dismiss()
            }
        }
        return view
    }
}