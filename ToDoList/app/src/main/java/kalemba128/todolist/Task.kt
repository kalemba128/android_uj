package kalemba128.todolist

import android.content.Context
import java.time.LocalDate
import java.util.UUID

enum class TaskStatus { ONGOING, DONE }

class Task(
    var id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String,
    var date: LocalDate?,
    var status: TaskStatus,
) {
    fun getIcon(): Int =
        if (status == TaskStatus.DONE) R.drawable.checked_24 else R.drawable.unchecked_24

    fun getIconColor(context: Context): Int =
        if (status == TaskStatus.DONE) com.google.android.material.R.color.bright_foreground_disabled_material_light else R.color.black
}