package kalemba128.shop.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kalemba128.shop.ui.main.MainViewModel
import kalemba128.shop.R

class CartFragment(private val viewModel: MainViewModel) : Fragment() {

    private lateinit var buyButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        val recycleView = root.findViewById(R.id.cartProductRV) as RecyclerView

        buyButton = root.findViewById(R.id.buyButton)

        buyButton.setOnClickListener {
            viewModel.buyProducts()
        }

        val adapter = CartAdapter(viewModel)
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter
        viewModel.cartProductsState.observe(
            viewLifecycleOwner
        ) { adapter.updateUserList(it) }

        return root
    }


}