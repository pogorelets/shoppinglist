package ru.helen.shoppinglist.ui.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_product.view.*
import ru.helen.shoppinglist.R
import ru.helen.shoppinglist.model.ProductOutput

class ProductAdapter(val listener: CheckListener) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    interface  CheckListener {
        fun onChangeCheck(isChecked: Boolean, product: ProductOutput)
    }
    private var data: List<ProductOutput> = ArrayList()

    fun swapData(data: List<ProductOutput>) {
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

    override fun onBindViewHolder(holder: ProductHolder, position: Int) = holder.bind(data[position], listener)

    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductOutput, listener: CheckListener) = with(itemView) {
            tvNameProduct.text = item.nameproduct
            check.isChecked = item.checking
            check.setOnCheckedChangeListener { buttonView, isChecked -> listener.onChangeCheck(check.isChecked, item) }

        }
    }
}