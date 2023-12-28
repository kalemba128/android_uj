package kalemba128.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {


    lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        val recycleView = root.findViewById(R.id.cartProductRV) as RecyclerView
        val adapter = CartAdapter(viewModel)
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter
        viewModel.cart.observe(
            viewLifecycleOwner
        ) { adapter.updateUserList(it) }

        return root
    }


}