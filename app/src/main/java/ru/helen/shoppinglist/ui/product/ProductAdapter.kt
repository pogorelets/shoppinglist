package ru.helen.shoppinglist.ui.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_product.view.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.entity.ProductsInList

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private var data: List<ProductsInList> = ArrayList()

    fun swapData(data: List<ProductsInList>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductHolder {
        return ProductHolder(
                LayoutInflater.from(parent?.context)
                        .inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) = holder.bind(data[position])

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductsInList) = with(itemView) {
            tvNameProduct.text = item.nameproduct
            check.isChecked = item.check

            setOnClickListener {

            }
        }
    }
}