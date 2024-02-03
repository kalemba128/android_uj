package kalemba128.shop.ui.cart

import kalemba128.shop.model.Product


data class CartProduct(val product: Product, var count: Int = 1)