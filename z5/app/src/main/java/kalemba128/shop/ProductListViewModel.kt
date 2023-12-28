package kalemba128.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductListViewModel : ViewModel() {

    val products = listOf(
        Product(
            "QuantumCore Processor",
            "Unleash unparalleled computing power with the QuantumCore processor, engineered for lightning-fast speeds and multitasking efficiency."
        ),
        Product(
            "LuminaGlow RGB RAM",
            "Illuminate your system with LuminaGlow RAM, featuring vibrant RGB lighting and high-speed performance for an immersive gaming experience."
        ),
        Product(
            "HyperFlow Liquid Cooling Kit:",
            "Keep your system cool under pressure with the HyperFlow Liquid Cooling Kit, delivering optimal thermal performance for high-performance PCs."
        ),
        Product(
            "TurboDrive SSD Accelerator",
            "Experience blazing-fast storage with the TurboDrive SSD Accelerator, enhancing data access speeds and reducing load times for seamless computing."
        ),
        Product(
            "SpectraSync Gaming Monitor",
            "Immerse yourself in stunning visuals with the SpectraSync Gaming Monitor, offering high refresh rates and vivid color reproduction for an unparalleled gaming display."
        ),
    )

    var cart: MutableLiveData<List<CartProduct>> = MutableLiveData()
    private var cartProducts = listOf<CartProduct>()


    fun add(product: Product) {
        val item = cartProducts.find { el -> el.product.name == product.name }
        if (item == null) cartProducts = cartProducts.plus(CartProduct(product))
        else item.count += 1
        cart.postValue(cartProducts)
    }

    fun remove(product: Product) {
        val item = cartProducts.find { e -> e.product.name == product.name }

        if (item != null) {
            if (item.count > 1) item.count -= 1
            else cartProducts = cartProducts.minus(item)
        }

        cart.postValue(cartProducts)
    }

}