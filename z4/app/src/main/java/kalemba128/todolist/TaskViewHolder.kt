package kalemba128.todolist

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kalemba128.todolist.databinding.TaskListItemBinding

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskListItemBinding,
    private val clickListener: TaskClickListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.name.text = task.name
        binding.description.text = task.description
        binding.date.text = task.getDate()?.format(Task.dateFormatter)
        binding.done.setImageResource(task.getIcon())
        binding.done.setColorFilter(task.getIconColor(context))

        binding.done.setOnClickListener {
            clickListener.toggle(task)
        }
    }
}