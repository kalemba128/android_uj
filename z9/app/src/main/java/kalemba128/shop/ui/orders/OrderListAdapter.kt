package kalemba128.shop.ui.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kalemba128.shop.databinding.OrderListTileBinding
import kalemba128.shop.model.Order
import kalemba128.shop.ui.main.MainViewModel

class OrderListAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(private val binding: OrderListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(o: Order) {
            binding.apply {
                order = o
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = OrderListTileBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product =  viewModel.orders[position]
        viewHolder.bind(product)
    }

    override fun getItemCount() = viewModel.orders.size
}

