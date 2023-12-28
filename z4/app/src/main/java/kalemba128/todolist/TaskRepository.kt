package kalemba128.todolist

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val items: Flow<List<Task>> = taskDao.getTasks()

    @WorkerThread
    suspend fun add(task: Task) {
        taskDao.add(task)
    }

    @WorkerThread
    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    @WorkerThread
    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}