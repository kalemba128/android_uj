package kalemba128.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kalemba128.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var currentOperator: String? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun numberAction(view: View) {
        if (view is Button) {
            binding.workingsTV.append(view.text)
        }
    }

    fun operatorAction(view: View) {
        if (view is Button) {
            val operation = view.text
            val first = getNumber(binding.resultsTV.text)
            val second = getNumber(binding.workingsTV.text)

            binding.workingsTV.text = ""

            if (binding.resultsTV.text.isEmpty()) {
                binding.resultsTV.text = second.toString()
            } else {
                val sum = first + second
                binding.resultsTV.text = sum.toString()

            }

            currentOperator = operation.toString()
        }
    }

    fun allClearAction(view: View) {
        binding.workingsTV.text = ""
        binding.resultsTV.text = ""
    }

    fun backSpaceAction(view: View) {
        val length = binding.workingsTV.length()
        if (length > 0) {
            binding.workingsTV.text = binding.workingsTV.text.subSequence(0, length - 1)
        }
    }

    fun equalsAction(view: View) {
        val first = getNumber(binding.resultsTV.text)
        val second = getNumber(binding.workingsTV.text)
        binding.resultsTV.text = ""
        binding.workingsTV.text = ""
        if (currentOperator == "+") {
            val result = first + second
            binding.resultsTV.text = result.toString()
        }
    }

    fun getNumber(sequence: CharSequence): Double {
        val text = sequence.toString()
        if (text.isEmpty()) {
            return 0.0
        } else {
            return text.toDouble()
        }
    }
}