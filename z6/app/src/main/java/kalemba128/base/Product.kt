package kalemba128.base

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Product : RealmObject {
    @PrimaryKey
    var id: String? = null
    var category: Category? = null
    var name: String? = null
    var description: String? = null


    constructor() {}

    constructor(
        id: String?,
        category: Category?,
        name: String?,
        description: String?
    ) {
        this.name = name
        this.id = id
        this.category = category
        this.description = description
    }


}