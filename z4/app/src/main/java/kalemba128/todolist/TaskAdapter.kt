package kalemba128.todolist

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kalemba128.todolist.databinding.TaskListItemBinding

class TaskAdapter(
    private val tasks: List<Task>,
    private val clickListener: TaskClickListener
) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskListItemBinding.inflate(from, parent, false)
        return TaskViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size
}