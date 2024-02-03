package kalemba128.shop.ui.products

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


class ProductsListFragment(private val viewModel: MainViewModel) : Fragment() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_product, container, false)
        val recycleView = rootView.findViewById(R.id.productsRV) as RecyclerView

        val adapter = ProductsListAdapter(viewModel)
        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.notifyDataSetChanged()
        }

        viewModel.getProducts()

        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter
        return rootView
    }
}