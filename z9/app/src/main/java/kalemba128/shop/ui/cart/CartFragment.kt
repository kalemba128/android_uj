package kalemba128.shop.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kalemba128.shop.ui.main.MainViewModel
import kalemba128.shop.R
import kotlinx.coroutines.runBlocking

class CartFragment(private val viewModel: MainViewModel) : Fragment() {

    private lateinit var buyButton: Button
    private lateinit var stripePaymentSheet: PaymentSheet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        val recycleView = root.findViewById(R.id.cartProductRV) as RecyclerView

        buyButton = root.findViewById(R.id.buyButton)

        buyButton.setOnClickListener { buyProducts() }

        val adapter = CartAdapter(viewModel)
        recycleView.layoutManager = LinearLayoutManager(activity)
        recycleView.adapter = adapter
        viewModel.cartProductsState.observe(
            viewLifecycleOwner
        ) { adapter.updateUserList(it) }

        stripePaymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
        return root
    }

    fun buyProducts() {
        runBlocking {
            val stripe = viewModel.createStripePayment()
            stripe?.let {
                PaymentConfiguration.init(requireContext(), stripe.publishableKey)
                stripePaymentSheet.presentWithPaymentIntent(
                    it.clientSecret,
                    PaymentSheet.Configuration(
                        merchantDisplayName = "StripeApp",
                        customer = PaymentSheet.CustomerConfiguration(
                            stripe.customerId,
                            stripe.ephemeralSecret,
                        )
                    )
                )
            }
        }

    }
    fun onPaymentSheetResult(result: PaymentSheetResult) {
        when (result) {

            is PaymentSheetResult.Completed -> {
                showToast("Payment completed")
            }

            is PaymentSheetResult.Canceled -> {
                showToast("Payment canceled")
            }

            is PaymentSheetResult.Failed -> {
                showToast("Payment failed")
            }
        }
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}