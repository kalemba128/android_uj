package kalemba128.network.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kalemba128.network.databinding.ProductListTileBinding
import kalemba128.network.models.Product


class ProductsAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(private val binding: ProductListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(p: Product) {
            binding.apply {
                product = p
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context;
        val binding =
            ProductListTileBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateProducts(newData: List<Product>) {
        products = newData
        notifyDataSetChanged()
    }

}

