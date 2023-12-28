package kalemba128.base

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category : RealmObject {
    @PrimaryKey
    var id: String? = null
    var name: String? = null

    var description: String? = null

    constructor() {}

    constructor(id: String?, name: String?, description: String?) {
        this.id = id
        this.name = name
        this.description = description
    }


}