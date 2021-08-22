package com.example.funnysoundsapp.fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.funnysoundsapp.R

class LoginFragment : Fragment()
{
    private lateinit var emailField : EditText
    private lateinit var passwordField : EditText
    private lateinit var loginButton : Button
    private lateinit var signUpButton : Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        emailField = view.findViewById(R.id.editTextEmail)
        passwordField = view.findViewById(R.id.editTextPassword)
        loginButton = view.findViewById(R.id.loginButton)
        signUpButton = view.findViewById(R.id.signUpButton)


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
}