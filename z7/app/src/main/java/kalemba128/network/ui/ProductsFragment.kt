package kalemba128.network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kalemba128.network.R


class ProductsFragment : Fragment() {
    lateinit var viewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PageViewModel::class.java]
        viewModel.getProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_product, container, false)

        val recycleView = rootView.findViewById(R.id.productsRV) as RecyclerView

        val adapter = ProductsAdapter(viewModel.products.value!!)

        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter

        viewModel.products.observe(this.viewLifecycleOwner) {
            adapter.updateProducts(it)
        }

        return rootView
    }


}