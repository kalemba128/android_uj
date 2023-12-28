package kalemba128.shop

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kalemba128.shop.databinding.ProductListTileBinding

class ProductAdapter(private var products: List<Product>, private val viewModel: ProductListViewModel) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

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
        context = parent.context
        val binding = ProductListTileBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = products[position]
        viewHolder.itemView.findViewById<ImageButton>(R.id.addProduct)
            .setOnClickListener { viewModel.add(product) }
        viewHolder.itemView.findViewById<LinearLayout>(R.id.productListTile).setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            val productJson = Gson().toJson(product)
            intent.putExtra("product", productJson)
            startActivity(context!!, intent, null)
        }
        viewHolder.bind(product)
    }

    override fun getItemCount() = products.size
}

