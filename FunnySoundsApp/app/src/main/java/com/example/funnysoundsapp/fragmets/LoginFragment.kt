package com.example.funnysoundsapp.fragmets

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.funnysoundsapp.R
import com.parse.*
import com.example.funnysoundsapp.MainActivity
import kotlin.properties.Delegates

class LoginFragment : Fragment()
{
    private lateinit var usernameField : EditText
    private lateinit var passwordField : EditText
    private lateinit var loginButton : Button
    private lateinit var signUpButton : Button
    private var progressDialog: ProgressDialog? = null
    private var usernameBool = false
    private var passwordBool = false
    private var isLoginDone = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
        progressDialog = ProgressDialog(this.context)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            //do nothing - just disabled back press
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        usernameField = view.findViewById(R.id.loginUsername)
        passwordField = view.findViewById(R.id.editTextPassword)
        loginButton = view.findViewById(R.id.loginBtn)
        signUpButton = view.findViewById(R.id.signUpButton)

        loginButton.setOnClickListener{
            validateForm()
            if(usernameBool && passwordBool)
            {
                Log.d("1", "here")
                login(usernameField.text.toString(), passwordField.text.toString())
            }
            else
            {
                val action = LoginFragmentDirections.fromLoginFragmentToWelcomeFragment()
                view.findNavController().navigate(action)
            }

        }

        signUpButton.setOnClickListener {
            val action = LoginFragmentDirections.fromLoginFragmentToRegisterFragment()
            view.findNavController().navigate(action)
        }

    }

    override fun onResume()
    {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop()
    {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }


    private fun validateForm()
    {
        if(usernameField.text.isEmpty())
        {
            usernameField.error = "You did not enter any username"
            usernameBool = false
        }
        else
        {
            usernameField.error = null
            usernameBool = true
        }

        if(passwordField.text.isEmpty())
        {
            passwordField.error = "You did not enter any password"
            passwordBool = false
        }
        else if(passwordField.text.length < 6)
        {
            passwordField.error = "The length of entered password has to be more than 5 characters"
            passwordBool = false
        }
        else
        {
            passwordField.error = null
            passwordBool = true
        }
    }

    private fun login(username: String, password: String){
        progressDialog?.show()
        ParseUser.logInInBackground(username,password) { parseUser: ParseUser?, parseException: ParseException? ->
            progressDialog?.dismiss()
            if (parseUser != null && parseException == null)
            {
                val action = LoginFragmentDirections.fromLoginFragmentToMainFragment()
                findNavController().navigate(action)
                showAlert("Successful Login", "Welcome back " + username + " !")
            }
            else
            {
                ParseUser.logOut()
                if (parseException != null)
                {
                    parseException.message?.let { showToast(it) }
                    usernameField.text.clear()
                    passwordField.text.clear()
                }
            }
        }
    }

    private fun showAlert(title: String, message: String)
    {
        val builder = AlertDialog.Builder(this.context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
            }
        val ok = builder.create()
        ok.show()
    }

    private fun showToast(message : String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

}