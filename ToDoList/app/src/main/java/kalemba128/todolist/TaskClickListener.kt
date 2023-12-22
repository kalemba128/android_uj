package kalemba128.todolist

interface TaskClickListener {
    fun toggle(task: Task)

    fun delete(position: Int)
}