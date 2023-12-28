package kalemba128.base


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject {
    @PrimaryKey
    var id: String? = null
    var name: String? = null
    var email: String? = null
    var cart: Cart? = null

    constructor() {}

    constructor(
        id: String?,
        name: String?,
        email: String?,
        cart: Cart?
    ) {
        this.id = id
        this.name = name
        this.email = email
        this.cart = cart
    }


}