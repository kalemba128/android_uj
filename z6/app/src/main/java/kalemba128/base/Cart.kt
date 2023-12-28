package kalemba128.base

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Cart : RealmObject {
    @PrimaryKey
    var id: String = ""
    var product: Product? = null
    var count = 0

    constructor() {}

    constructor(id: String, product: Product?, quantity: Int) {
        this.id = id
        this.product = product
        this.count = quantity
    }


}