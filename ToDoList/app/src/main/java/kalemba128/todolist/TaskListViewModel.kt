package kalemba128.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskListViewModel : ViewModel() {
    var tasks = MutableLiveData<MutableList<Task>>()

    init {
        tasks.value = mutableListOf()
    }

    fun add(task: Task) {
        val items = tasks.value
        items!!.add(task)
        tasks.postValue(items)
    }

    fun toggle(task: Task) {
        val items = tasks.value
        val task = items!!.find { it.id == task.id }!!
        if (task.status == TaskStatus.DONE) task.status = TaskStatus.ONGOING
        else task.status = TaskStatus.DONE
        tasks.postValue(items)
    }

    fun delete(position: Int) {
        val items = tasks.value
        val task = tasks.value!![position]
        tasks.value!!.remove(task)
        tasks.postValue(items)
    }
}