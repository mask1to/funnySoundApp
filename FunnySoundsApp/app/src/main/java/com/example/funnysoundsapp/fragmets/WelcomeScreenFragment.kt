package com.example.funnysoundsapp.fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.funnysoundsapp.R

class WelcomeScreenFragment : Fragment()
{

    private lateinit var mainButton : Button
    private lateinit var headingView: ImageView
    private lateinit var mainAnimation : LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            //do nothing - just disabled back press
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.welcome_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        mainButton = view.findViewById(R.id.mainBtn)
        headingView = view.findViewById(R.id.headingImageView)
        mainAnimation = view.findViewById(R.id.mainAnimation)

        mainAnimation.repeatCount = Animation.INFINITE

        mainButton.setOnClickListener {
            val action = WelcomeScreenFragmentDirections.fromWelcomeFragmentToLoginFragment()
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
}