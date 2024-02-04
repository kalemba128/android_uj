package kalemba128.shop.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kalemba128.shop.ui.cart.CartFragment
import kalemba128.shop.ui.orders.OrdersListFragment
import kalemba128.shop.ui.products.ProductsListFragment

class TabsPagerAdapter(
    private val context: Context, fm: FragmentManager,
    private val viewModel: MainViewModel
) :
    FragmentPagerAdapter(fm) {


    private val titles = listOf("Products", "Cart", "Orders")
    private val fragments = listOf(ProductsListFragment(viewModel), CartFragment(viewModel), OrdersListFragment(viewModel))
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}