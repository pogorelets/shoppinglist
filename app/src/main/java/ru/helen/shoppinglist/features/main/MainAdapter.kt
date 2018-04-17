package ru.helen.shoppinglist.features.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_shoppinglist.view.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Shoppinglist
import java.util.*

/**
 * Created by lenap on 19.03.2018.
 */
class MainAdapter(val listener: Contract.ViewMain) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var data: List<Shoppinglist> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_shoppinglist, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) = holder.bind(data[position], listener)

    fun swapData(data: List<Shoppinglist>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Shoppinglist, listener: Contract.ViewMain) = with(itemView) {
            tvNameList.text = item.namelist
            setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }
}