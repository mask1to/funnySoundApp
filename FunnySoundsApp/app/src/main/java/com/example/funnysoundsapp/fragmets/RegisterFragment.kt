package com.example.funnysoundsapp.fragmets

import android.app.AlertDialog
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
import android.widget.Button


class RegisterFragment : Fragment()
{
    private lateinit var theEmail : EditText
    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    private lateinit var firstPassword : EditText
    private lateinit var secondPassword : EditText
    private lateinit var signUpButton : Button

    var isEmailCorrect = false
    var passwordMatch = false
    var passwordCorrect = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        theEmail = view.findViewById(R.id.editTextEmailField)
        firstName = view.findViewById(R.id.editTextFirstName)
        lastName = view.findViewById(R.id.editTextLastName)
        firstPassword = view.findViewById(R.id.editTextPasswordRegister)
        secondPassword = view.findViewById(R.id.editTextPasswordRegister2)
        signUpButton = view.findViewById(R.id.signUpButtonRegister)

        view.findViewById<View>(R.id.signUpButtonRegister).setOnClickListener(View.OnClickListener {
            validateForm()
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
        if(theEmail.text.isEmpty())
        {
            theEmail.error = "You did not enter any e-mail address"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(theEmail.text.toString()).matches())
        {
            theEmail.error = "You have entered wrong format of e-mail address"
        }
        else
        {
            theEmail.error = null
        }

        if(firstName.text.isEmpty())
        {
            firstName.error = "You have to enter your First Name"
        }

        if(lastName.text.isEmpty())
        {
            lastName.error = "You have to enter your Last Name"
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
        else if(firstPassword.text != secondPassword.text)
        {
            firstPassword.error = "Passwords do not match"
        }
        else
        {
            firstPassword.error = null
            secondPassword.error = null
        }
    }

}