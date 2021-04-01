package com.arsenbasha.kotlin.ui.home

import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.arsenbasha.kotlin.R
import com.arsenbasha.kotlin.databinding.FragmentHomeBinding


/**
 * Leandro Paredes
 * */
class HomeFragment : Fragment() {

    private lateinit var play: LinearLayout

    private lateinit var stop: LinearLayout
    var isstop = true
    var ispause = true
    private lateinit var status: ImageView

    private lateinit var pause: LinearLayout
    private lateinit var video: VideoView
    private lateinit var homeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeBinding = FragmentHomeBinding.bind(root)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        video = homeBinding.videoNav
        video.setVideoURI(Uri.parse(ruta()))
        play = homeBinding.play
        pause = homeBinding.pausa
        stop = homeBinding.stop
        status = homeBinding.status
        stop.setOnClickListener { stop() }
        play.setOnClickListener { play() }
        pause.setOnClickListener { pause() }

        video.setOnTouchListener { v, event ->
            status.visibility=View.VISIBLE
            pauseStartOnVideo()
            hideicon()
            false
        }
    }


    private fun stop() {
        video.stopPlayback()
        video.seekTo(0)
        pause.visibility = View.INVISIBLE
        play.visibility = View.VISIBLE
        isstop = true
        ispause = false
    }
    private fun hideicon(){
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() { status.visibility=View.INVISIBLE }
            override fun onTick(millisUntilFinished: Long) {}
        }.start()
    }

    private fun pauseStartOnVideo() = if (ispause) play() else pause()

    private fun changeIconStatus() = if (ispause) status.setImageResource(R.drawable.play_24)
    else status.setImageResource(R.drawable.pause_24)


    private fun play() {
        if (isstop) {
            video.setVideoURI(Uri.parse(ruta()))
            isstop = false
            video.start()
        } else  video.start()
        play.visibility = View.INVISIBLE
        pause.visibility = View.VISIBLE
        ispause = false
        changeIconStatus()
        hideicon()
    }

    private fun pause() {
        video.pause()
        ispause = true
        pause.visibility = View.INVISIBLE
        play.visibility = View.VISIBLE
        changeIconStatus()
        hideicon()
    }

    private fun ruta(): String = "android.resource://" + activity?.packageName + "/" + R.raw.video


}