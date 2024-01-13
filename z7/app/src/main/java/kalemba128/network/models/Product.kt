package kalemba128.network.models

import androidx.room.*

@Entity()
data class Product (
    @ColumnInfo(name = "categoryId") val categoryId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double){
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}



