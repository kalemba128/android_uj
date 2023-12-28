package kalemba128.todolist

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: LiveData<List<Task>> = repository.items.asLiveData()


    fun add(task: Task) = viewModelScope.launch {
        repository.add(task)
    }

    fun toggle(task: Task) = viewModelScope.launch {

        if (task.status == TaskStatus.DONE) task.status = TaskStatus.ONGOING
        else task.status = TaskStatus.DONE

        repository.update(task)
    }

    fun delete(position: Int) = viewModelScope.launch {
        val task = tasks.value!![position]
        repository.delete(task)
    }
}

class TaskListViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java))
            return TaskListViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}