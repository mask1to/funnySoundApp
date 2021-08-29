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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.funnysoundsapp.R
import com.parse.*

class LoginFragment : Fragment()
{
    private lateinit var usernameField : EditText
    private lateinit var passwordField : EditText
    private lateinit var loginButton : Button
    private lateinit var signUpButton : Button
    private var progressDialog: ProgressDialog? = null
    var usernameBool = false
    var passwordBool = false
    var success = false
    lateinit var query : ParseQuery<ParseObject>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
        progressDialog = ProgressDialog(this.context)
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
                login(usernameField.text.toString(), passwordField.text.toString())
                val action = LoginFragmentDirections.fromLoginFragmentToMainFragment()
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

    fun login(username: String, password: String)
    {
        progressDialog?.show()
        ParseUser.logInInBackground(username,password) { parseUser: ParseUser?, parseException: ParseException? ->
            progressDialog?.dismiss()
            if (parseUser != null) {
                showAlert("Successful Login", "Welcome back " + username + " !")
            } else {
                ParseUser.logOut()
                if (parseException != null) {
                    parseException.message?.let { showToast(it) }
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