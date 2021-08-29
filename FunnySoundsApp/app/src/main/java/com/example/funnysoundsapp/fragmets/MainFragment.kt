package com.example.funnysoundsapp.fragmets

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.funnysoundsapp.R
import com.parse.ParseException
import com.parse.ParseUser

class MainFragment : Fragment()
{
    private lateinit var logOutBtn : Button
    private var progressDialog: ProgressDialog? = null

    private lateinit var firstBtn : Button
    private lateinit var secondBtn : Button
    private lateinit var thirdBtn : Button
    private lateinit var fourthBtn : Button
    private lateinit var fifthBtn : Button
    private lateinit var sixthBtn : Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
        progressDialog = ProgressDialog(this.context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        firstBtn = view.findViewById(R.id.oneBtn)
        secondBtn = view.findViewById(R.id.twoBtn)
        thirdBtn = view.findViewById(R.id.threeBtn)
        fourthBtn = view.findViewById(R.id.fourBtn)
        fifthBtn = view.findViewById(R.id.fiveBtn)
        sixthBtn = view.findViewById(R.id.sixBtn)

        logOutBtn = view.findViewById(R.id.logoutBtn)
        logOutBtn.setOnClickListener {
            progressDialog!!.show()
            ParseUser.logOutInBackground { e: ParseException? ->
                progressDialog!!.dismiss()
                if (e == null)
                {
                    showAlert("So, you're going...", "Ok...Bye-bye then")
                    val action = MainFragmentDirections.fromMainFragmentToWelcomeFragment()
                    view.findNavController().navigate(action)
                }

            }
        }
    }

    override fun onResume()
    {
        super.onResume()
    }

    override fun onStop()
    {
        super.onStop()
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
}