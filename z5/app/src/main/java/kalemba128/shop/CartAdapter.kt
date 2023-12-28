package kalemba128.shop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kalemba128.shop.databinding.CartProductListTileBinding

class CartAdapter(private val viewModel: ProductListViewModel) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var data: List<CartProduct> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserList(newData: List<CartProduct>) {
        data = newData
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CartProductListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(p: CartProduct) {
            binding.apply {
                product = p
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CartProductListTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = data[position]
        viewHolder.itemView.findViewById<Button>(R.id.addOneButton).setOnClickListener {
            viewModel.add(product.product)
        }

        viewHolder.itemView.findViewById<Button>(R.id.removeOneButton).setOnClickListener {
            viewModel.remove(product.product)
        }

        viewHolder.bind(product)
    }

    override fun getItemCount() = data.size



}