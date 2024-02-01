package com.kalemba128.auth.ui.signup

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kalemba128.auth.R
import com.kalemba128.auth.ui.main.MainViewModel


class SignUpFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var loginTextField: EditText
    private lateinit var passwordTextField: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpViewModel = ViewModelProvider(requireActivity())[SignUpViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        signInButton = _view.findViewById(R.id.registerNavButton)
        signUpButton = _view.findViewById(R.id.registerButton)
        loginTextField = _view.findViewById(R.id.username)
        passwordTextField = _view.findViewById(R.id.password)

        signInButton.setOnClickListener {
            Navigation.findNavController(_view).navigate(R.id.navigateToLogin)
        }
        signUpButton.setOnClickListener { signUp() }

        return _view
    }

    fun signUp() {
        val login = loginTextField.text.toString()
        val password = passwordTextField.text.toString()

        val response = signUpViewModel.signUp(login, password)

        if (response.isSuccessful) {
            mainViewModel.user = response.body()!!.user
            Navigation.findNavController(_view).navigate(R.id.navigateToMainScreen)

        } else {
            showToast("Error, something went wrong")
        }

    }

    fun showToast(text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}