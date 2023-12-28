package kalemba128.todolist

import android.app.Application

class App : Application() {
    private val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}