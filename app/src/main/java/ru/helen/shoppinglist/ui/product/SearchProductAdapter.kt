package ru.helen.shoppinglist.ui.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_search_product.view.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.Product
import java.util.*

class SearchProductAdapter(val listener: ClickSearchListener) : RecyclerView.Adapter<SearchProductAdapter.ViewHolder>() {
    interface ClickSearchListener {
        fun onSearchProductClick(item: Product)
    }
    private var data: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_search_product, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position], listener)

    fun swapData(data: List<Product>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product, listener: ClickSearchListener) = with(itemView) {
            itemView.tvNameProduct.text = item.nameproduct
            setOnClickListener {
                listener.onSearchProductClick(item)
            }
        }
    }
}