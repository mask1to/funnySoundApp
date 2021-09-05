package com.example.funnysoundsapp.fragmets.slides

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.funnysoundsapp.R

class WatchFragment1 : Fragment()
{
    private lateinit var theImage : ImageView
    private lateinit var firstButton: Button
    private lateinit var secondButton: Button
    private lateinit var thirdButton: Button
    private lateinit var fourthButton: Button
    private lateinit var textViewHeading : TextView
    private lateinit var textView1 : TextView
    private lateinit var textView2 : TextView
    private lateinit var textView3 : TextView
    private lateinit var textView4 : TextView
    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var mediaPlayer3: MediaPlayer
    private lateinit var mediaPlayer4: MediaPlayer

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

        theImage = view.findViewById(R.id.imageViewDanko)
        firstButton = view.findViewById(R.id.soundDanko1)
        secondButton = view.findViewById(R.id.soundDanko2)
        thirdButton = view.findViewById(R.id.soundDanko3)
        fourthButton = view.findViewById(R.id.soundDanko4)
        textViewHeading = view.findViewById(R.id.textViewDanko)
        textView1 = view.findViewById(R.id.textViewSound1)
        textView2 = view.findViewById(R.id.textViewSound2)
        textView3 = view.findViewById(R.id.textViewSound3)
        textView4 = view.findViewById(R.id.textViewSound4)

        mediaPlayer1 = MediaPlayer.create(this.context, R.raw.jasitouzivam)
        mediaPlayer2 = MediaPlayer.create(this.context, R.raw.otupejevjavu)
        mediaPlayer3 = MediaPlayer.create(this.context, R.raw.poslankyne)
        mediaPlayer4 = MediaPlayer.create(this.context, R.raw.chskym)

        mediaPlayer1.setOnCompletionListener {
            firstButton.text = getString(R.string.mediaPlayerPlay)
        }

        mediaPlayer2.setOnCompletionListener {
            secondButton.text = getString(R.string.mediaPlayerPlay)
        }

        mediaPlayer3.setOnCompletionListener {
            thirdButton.text = getString(R.string.mediaPlayerPlay)
        }

        mediaPlayer4.setOnCompletionListener {
            fourthButton.text = getString(R.string.mediaPlayerPlay)
        }

        firstButton.setOnClickListener {
            if(!mediaPlayer1.isPlaying)
            {
                mediaPlayer1.start()
                firstButton.text = getString(R.string.mediaPlayerPause)
            }
            else
            {
                mediaPlayer1.pause()
                firstButton.text = getString(R.string.mediaPlayerPlay)
            }
        }

        secondButton.setOnClickListener {
            if(!mediaPlayer2.isPlaying)
            {
                mediaPlayer2.start()
                secondButton.text = getString(R.string.mediaPlayerPause)
            }
            else
            {
                mediaPlayer2.pause()
                secondButton.text = getString(R.string.mediaPlayerPlay)
            }
        }

        thirdButton.setOnClickListener {
            if(!mediaPlayer3.isPlaying)
            {
                mediaPlayer3.start()
                thirdButton.text = getString(R.string.mediaPlayerPause)
            }
            else
            {
                mediaPlayer3.pause()
                thirdButton.text = getString(R.string.mediaPlayerPlay)
            }
        }

        fourthButton.setOnClickListener {
            if(!mediaPlayer4.isPlaying)
            {
                mediaPlayer4.start()
                fourthButton.text = getString(R.string.mediaPlayerPause)
            }
            else
            {
                mediaPlayer4.pause()
                fourthButton.text = getString(R.string.mediaPlayerPlay)
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
}