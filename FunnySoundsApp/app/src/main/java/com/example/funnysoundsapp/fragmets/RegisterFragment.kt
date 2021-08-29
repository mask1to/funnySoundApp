package com.example.funnysoundsapp.fragmets

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.funnysoundsapp.R
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.os.HandlerCompat.postDelayed
import androidx.navigation.findNavController
import com.parse.*


class RegisterFragment : Fragment()
{
    private lateinit var theUsername : EditText
    private lateinit var firstPassword : EditText
    private lateinit var secondPassword : EditText
    private lateinit var signUpButton : Button
    private var progressDialog: ProgressDialog? = null

    var isEmailCorrect = false
    var passwordMatch = false


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
        progressDialog = ProgressDialog(this.context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        theUsername = view.findViewById(R.id.editTextUsername)
        firstPassword = view.findViewById(R.id.editTextPasswordRegister)
        secondPassword = view.findViewById(R.id.editTextPasswordRegister2)
        signUpButton = view.findViewById(R.id.signUpButtonRegister)

        view.findViewById<View>(R.id.signUpButtonRegister).setOnClickListener(View.OnClickListener {
            validateForm()
            if(isEmailCorrect && passwordMatch)
            {
                signup(theUsername.text.toString(), firstPassword.text.toString())
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                view.findNavController().navigate(action)
            }
        })
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
        if(theUsername.text.isEmpty())
        {
            theUsername.error = "You did not enter any username"
            isEmailCorrect = false
        }
        else
        {
            theUsername.error = null
            isEmailCorrect = true
        }

        if(firstPassword.text.isEmpty() || secondPassword.text.isEmpty())
        {
            firstPassword.error = "You did not enter any password"
            secondPassword.error = "You did not enter any password for confirmation"
        }
        else if(firstPassword.text.length < 6 || secondPassword.text.length < 6)
        {
            firstPassword.error = "The length of entered password has to be more than 5 characters"
            secondPassword.error = "The length of entered password has to be more than 5 characters"
        }

        if(firstPassword.text.toString() == secondPassword.text.toString())
        {
            firstPassword.error = null
            secondPassword.error = null
            passwordMatch = true
        }
        else
        {
            firstPassword.error = "Passwords do not match"
            secondPassword.error = "Passwords do not match"
            passwordMatch = false
        }
    }

    fun signup(username: String, password: String)
    {
        progressDialog?.show()

        val user = ParseUser()
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(SignUpCallback() {
            progressDialog?.dismiss()
            if (it == null) {
                showAlert("Successful Sign Up!", "Welcome " + username + " !");
            } else {
                ParseUser.logOut();
                it.message?.let { it1 -> showToast(it1) }
            }
        })
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