package com.kalemba128.auth.ui.signin

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kalemba128.auth.GithubConfig
import com.kalemba128.auth.R
import com.kalemba128.auth.ui.main.MainViewModel
import kotlinx.coroutines.*
import java.lang.Exception

class SignInFragment : Fragment() {

    private lateinit var _view: View
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var mainViewModel: MainViewModel

    private lateinit var signUpButton: Button
    private lateinit var signInButton: Button

    private lateinit var loginTextField: EditText
    private lateinit var passwordTextField: EditText

    private lateinit var googleButton: ImageView
    private lateinit var githubButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        signInViewModel = ViewModelProvider(requireActivity())[SignInViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        signUpButton = _view.findViewById(R.id.signUpButton)
        signInButton = _view.findViewById(R.id.signInButton)
        loginTextField = _view.findViewById(R.id.loginTextField)
        passwordTextField = _view.findViewById(R.id.passwordTextField)
        googleButton = _view.findViewById(R.id.googleButton)
        githubButton = _view.findViewById(R.id.githubButton)

        signUpButton.setOnClickListener {
            Navigation.findNavController(_view).navigate(R.id.navigateToRegister)
        }

        signInButton.setOnClickListener { signInServer() }
        googleButton.setOnClickListener { signInGoogle() }
        githubButton.setOnClickListener { signInGithub() }

        return _view
    }

    fun signInGoogle() {
        val clientId = ""
        val options =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(clientId)
                .build()
        val client = GoogleSignIn.getClient(requireActivity(), options)
        val signInIntent = client.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    fun signInGithub() {
        val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(GithubConfig.oauth2Url))
        startActivity(githubIntent)
    }

    override fun onResume() {
        super.onResume()
        val activity = activity
        if (activity != null) {
            val intent = activity.intent
            val uri = intent.data

            if (uri != null && uri.toString().startsWith(GithubConfig.redirectUri)) {
                val code = uri.getQueryParameter("code")
                if (code != null) {
                    val user = signInViewModel.signInGithub(code)
                    if (user != null) {
                        mainViewModel.user = user
                        Navigation.findNavController(_view).navigate(R.id.navigateToMainScreen)
                    } else {
                        showToast("Error, something went wrong")
                    }
                } else {
                    showToast("Error, something went wrong")
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.result
                val account = GoogleSignIn.getLastSignedInAccount(requireContext())
                if (account != null) {
                    val user = signInViewModel.signInGoogle(account)
                    if (user != null) {
                        mainViewModel.user = user
                        Navigation.findNavController(_view).navigate(R.id.navigateToMainScreen)
                    } else {
                        showToast("Error, something went wrong")
                    }
                } else {
                    showToast("Error, something went wrong")
                }
            } catch (e: Exception) {
                showToast("Error, something went wrong")
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun signInServer() {
        val login = loginTextField.text.toString()
        val password = passwordTextField.text.toString()
        GlobalScope.launch(Dispatchers.IO) {
            val user = signInViewModel.signIn(login, password, "", "SERVER")
            if (user != null) {
                mainViewModel.user = user
            } else {
                showToast("Error, something went wrong")
            }
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