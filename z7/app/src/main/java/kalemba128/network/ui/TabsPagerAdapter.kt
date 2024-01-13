package kalemba128.network.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    val titles = listOf<String>(
        "Products",
        "Categories",
        "Add product",
    )

    val fragments = listOf<Fragment>(
        ProductsFragment(),
        CategoriesFragment(),
        AddProductFragment(),
    )

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