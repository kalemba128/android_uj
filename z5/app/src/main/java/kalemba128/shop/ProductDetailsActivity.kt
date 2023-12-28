package kalemba128.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import kalemba128.shop.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)


        val bundle: Bundle = intent.extras!!

        val productJsonString: String = bundle.get("product") as String
        val binding: ActivityProductDetailsBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_product_details
        )
        binding.product = Gson().fromJson(productJsonString, Product::class.java)

        findViewById<Button>(R.id.closeDetailsButton).setOnClickListener {
            this.finish()
        }
    }
}