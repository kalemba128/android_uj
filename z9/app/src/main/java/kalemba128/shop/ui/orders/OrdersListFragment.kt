package kalemba128.shop.ui.orders

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kalemba128.shop.R
import kalemba128.shop.ui.main.MainViewModel


class OrdersListFragment(private val viewModel: MainViewModel) : Fragment() {
    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_orders, container, false)
        val recycleView = rootView.findViewById(R.id.ordersRV) as RecyclerView

        val adapter = OrderListAdapter(viewModel)
        viewModel.ordersState.observe(viewLifecycleOwner) { orders ->
            adapter.notifyDataSetChanged()
        }

        viewModel.getOrders()

        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter
        return rootView
    }
}