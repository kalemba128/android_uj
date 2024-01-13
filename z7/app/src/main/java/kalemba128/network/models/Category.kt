package kalemba128.network.models

import androidx.room.*

@Entity()
data class Category(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imgUrl") val imgUrl: String
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

    override fun toString(): String {
        return name
    }
}

