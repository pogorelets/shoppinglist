package ru.helen.shoppinglist.ui.main

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.helen.shoppinglist.R

class DialogConfirmDelete : BottomSheetDialogFragment() {


    companion object {
        private const val ID = "id"
        fun newInstance(id: Long): DialogConfirmDelete {
            val result = DialogConfirmDelete()
            val bundle = Bundle()
            bundle.putLong(ID, id)
            result.arguments = bundle
            return result
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.dialog_confirm_delete, container, false)
        val listener = activity as Contract.DeleteListener
        val args = arguments
        view.findViewById<AppCompatButton>(R.id.btnCancel).setOnClickListener {
            dismiss()
        }

        view.findViewById<AppCompatButton>(R.id.btnSave).setOnClickListener {
            listener.onDeleteListener(args.getLong(ID))
            dismiss()
        }

        return view
    }
}