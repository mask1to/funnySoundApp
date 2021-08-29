package com.example.funnysoundsapp.fragmets.slides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.funnysoundsapp.R

class SlideFragment1 : Fragment()
{
    private lateinit var viewPager : ViewPager
    private lateinit var linearDotLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        parentFragment?.activity?.actionBar?.hide()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.mainfirst, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.viewPager)
        linearDotLayout = view.findViewById(R.id.dotsLayout)


    }

    override fun onResume()
    {
        super.onResume()
    }

    override fun onStop()
    {
        super.onStop()
    }
}