package kalemba128.todolist

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kalemba128.todolist.databinding.FragmentNewTaskSheetBinding
import java.time.LocalDate
import java.util.Calendar

class CreateTaskSheet : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskListViewModel: TaskListViewModel
    private var date: LocalDate? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskListViewModel = ViewModelProvider(activity)[TaskListViewModel::class.java]
        binding.saveButton.setOnClickListener {
            create()
        }
        binding.timePickerButton.setOnClickListener {
            pickDate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun pickDate() {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            this,
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun create() {
        val task = Task(
            name = binding.name.text.toString(),
            description = binding.desc.text.toString(),
            dateString = date?.format(Task.dateFormatter),
            status = TaskStatus.ONGOING,
        )
        taskListViewModel.add(task)
        dismiss()
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = LocalDate.of(year, month, dayOfMonth)
    }
}