package kalemba128.network.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kalemba128.network.R
import kalemba128.network.models.Product
import kotlinx.coroutines.runBlocking

class AddProductFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var viewModel: PageViewModel

    private lateinit var addProductButton: Button
    private lateinit var nameField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var priceField: EditText
    private lateinit var categoryField: Spinner


    private var categoryId: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PageViewModel::class.java]
        viewModel.getCategories()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_product, container, false)

        categoryField = rootView.findViewById(R.id.category)

        categoryField.adapter =
            ArrayAdapter(requireContext(), R.layout.item_dropdown, viewModel.categories.value!!)

        categoryField.onItemSelectedListener = this

        viewModel.categories.observe(this.viewLifecycleOwner) {
            categoryField.adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, it)
        }

        nameField = rootView.findViewById(R.id.name)
        descriptionField = rootView.findViewById(R.id.description)
        priceField = rootView.findViewById(R.id.price)

        addProductButton = rootView.findViewById(R.id.btnCreateProduct)
        addProductButton.setOnClickListener {
            val name = nameField.text.toString()
            val description = descriptionField.text.toString()
            val price = priceField.text.toString()
            val context = requireContext()
            if (!listOf(name, description, price).any { it.isEmpty() }) {
                runBlocking {
                    val product = Product(categoryId, name, description, price.toDouble())
                    val response = viewModel.api.addProduct(product)
                    if (response.isSuccessful) {
                        showToast(context, "Product created successfully")
                        viewModel.getProducts()
                        nameField.text.clear()
                        descriptionField.text.clear()
                        priceField.text.clear()
                    } else {
                        showToast(context, "Problem occured")
                    }
                }
            } else {
                showToast(context, "All fields required")
            }
        }

        return rootView
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val category = viewModel.categories.value!![p2]
        categoryId = category.id
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    private fun showToast(context: Context?, text: CharSequence?) {
        Toast.makeText(context, text, LENGTH_LONG).show()
    }

}
