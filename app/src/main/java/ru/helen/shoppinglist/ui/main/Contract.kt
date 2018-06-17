package ru.helen.shoppinglist.ui.main

import ru.helen.shoppinglist.model.QuantProductInList

/**
 * Created by elena on 15.06.18.
 */
class Contract {
    interface DialogCreateListener {
        fun insertList(nameList: String)
    }

    interface ListClick{
        fun onListClick(list: QuantProductInList)
        fun onLongListClick(list: QuantProductInList)
    }

    interface ListEventsListener{
        fun onConfirmDeleteList(id: Long)
        fun onRenameList(id: Long, oldName: String)
        fun onCopyList(id: Long, name: String)
        fun onShareList()
    }

    interface DeleteListener{
        fun onDeleteListener(id: Long)
    }

}