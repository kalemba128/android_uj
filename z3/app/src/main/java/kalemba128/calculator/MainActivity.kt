package kalemba128.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberAction(view: View) {}

    fun operatorAction(view: View) {}
    fun allClearAction(view: View) {}
    fun backSpaceAction(view: View) {}
    fun equalsAction(view: View) {}
}