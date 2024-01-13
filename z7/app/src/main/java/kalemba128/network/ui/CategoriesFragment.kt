package kalemba128.network.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kalemba128.network.R

class CategoriesFragment : Fragment() {

    lateinit var viewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PageViewModel::class.java]
        viewModel.getCategories()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)

        val recycleView = root.findViewById(R.id.basketProductRV) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(activity)

        val adapter = CategoriesAdapter(viewModel.categories.value!!)
        recycleView.adapter = adapter

        viewModel.categories.observe(this.viewLifecycleOwner) {
            adapter.updateCategories(it)
        }
        return root
    }
}