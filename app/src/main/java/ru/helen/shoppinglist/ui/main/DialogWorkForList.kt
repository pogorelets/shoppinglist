package ru.helen.shoppinglist.ui.main

import android.support.design.widget.BottomSheetDialogFragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.model.QuantProductInList


class DialogWorkForList : BottomSheetDialogFragment() {
    private lateinit var listener: Contract.ListEventsListener

    companion object {
        private const val ID = "id"
        private const val NAME_LIST = "nameList"
        lateinit var list: QuantProductInList
        fun newInstance(id: Long, nameList: String): DialogWorkForList {
            val result = DialogWorkForList()
            val bundle = Bundle()
            bundle.putLong(ID, id)
            bundle.putString(NAME_LIST, nameList)
            result.arguments = bundle
            return result

        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,  savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.shoppinglist_dialog, container, false)
        listener = activity as Contract.ListEventsListener
        val args = arguments
        view.findViewById<TextView>(R.id.deleteList).setOnClickListener {
            listener.onConfirmDeleteList(args.getLong(ID))
        }
        view.findViewById<TextView>(R.id.editList).setOnClickListener {
            listener.onRenameList(args.getLong(ID), args.getString(NAME_LIST))
        }
        view.findViewById<TextView>(R.id.copyList).setOnClickListener {
            listener.onCopyList(args.getLong(ID), args.getString(NAME_LIST))
        }
        view.findViewById<TextView>(R.id.shareList).setOnClickListener {
            listener.onShareList()
        }
        return view
    }
}