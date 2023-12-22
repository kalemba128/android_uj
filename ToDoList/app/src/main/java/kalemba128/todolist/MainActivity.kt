package kalemba128.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kalemba128.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskListViewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskListViewModel = ViewModelProvider(this)[TaskListViewModel::class.java]

        binding.newTaskButton.setOnClickListener {
            CreateTaskSheet().show(supportFragmentManager, "newTaskTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val mainActivity = this
        taskListViewModel.tasks.observe(this) {
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskAdapter(it, mainActivity)
            }
        }

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                delete(swipeDir)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.todoListRecyclerView)

    }

    override fun toggle(task: Task) {
        taskListViewModel.toggle(task)
    }

    override fun delete(position: Int) {
        taskListViewModel.delete(position)
    }
}