package com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.takehomechallenge.belajar_restfulapi_trisna_sejati.R
import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.model.ProductsItem

class ProductAdapter(private val onClick: (ProductsItem) -> Unit) :
ListAdapter<ProductsItem, ProductAdapter.ProductViewHolder>(ProductCallBack){
    class ProductViewHolder(itemView: View, val onClick: (ProductsItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

            private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
            private val title: TextView = itemView.findViewById(R.id.title)
            private val brand: TextView = itemView.findViewById(R.id.brand)
            private val price: TextView = itemView.findViewById(R.id.price)

            private var currentProduct: ProductsItem? = null

            init {
                itemView.setOnClickListener {
                    currentProduct?.let {
                        onClick(it)
                    }
                }
            }

        fun bind(productsItem: ProductsItem) {
            currentProduct = productsItem

            title.text = productsItem.title
            brand.text = productsItem.brand
            price.text = productsItem.price.toString()

            Glide.with(itemView).load(productsItem.thumbnail).centerCrop().into(thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        return ProductViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productsItem = getItem(position)
        holder.bind(productsItem)
    }
}
object ProductCallBack : DiffUtil.ItemCallback<ProductsItem>(){
    override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
        return oldItem.id == newItem.id
    }

}