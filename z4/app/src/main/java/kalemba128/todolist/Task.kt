package kalemba128.todolist

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class TaskStatus { ONGOING, DONE }

@Entity(tableName = "tasks")
class Task(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description")var description: String,
    @ColumnInfo(name = "date") var dateString: String?,
    @ColumnInfo(name = "status") var status: TaskStatus,
) {
    fun getIcon(): Int =
        if (status == TaskStatus.DONE) R.drawable.checked_24 else R.drawable.unchecked_24

    fun getIconColor(context: Context): Int =
        if (status == TaskStatus.DONE) com.google.android.material.R.color.bright_foreground_disabled_material_light else R.color.black

    fun getDate(): LocalDate? = if (dateString == null) null else LocalDate.parse(dateString, dateFormatter)

    companion object {
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }

}