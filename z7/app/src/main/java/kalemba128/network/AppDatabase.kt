package kalemba128.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kalemba128.network.models.Category
import kalemba128.network.models.Product
import kalemba128.network.models.dao.CategoryDao
import kalemba128.network.models.dao.ProductDao

@Database(entities = [Product::class, Category::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        private var instance: AppDatabase ?= null
        fun getInstance(context: Context) : AppDatabase?{
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "network_database"
                ).fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}
