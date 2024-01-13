package kalemba128.network.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kalemba128.network.databinding.CategoryListTileBinding
import kalemba128.network.models.Category

class CategoriesAdapter(private var data: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: CategoryListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category1: Category) {
            binding.apply {
                category = category1
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategories(newData: List<Category>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryListTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }

    override fun getItemCount() = data.size
}