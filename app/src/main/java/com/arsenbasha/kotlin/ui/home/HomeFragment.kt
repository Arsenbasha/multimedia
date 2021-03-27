package com.arsenbasha.kotlin.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arsenbasha.kotlin.R
import com.arsenbasha.kotlin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeBinding = FragmentHomeBinding.bind(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val video = homeBinding.video

        video.setVideoURI(Uri.parse(ruta()))
        val play = homeBinding.play
        val pause = homeBinding.pausa
        val stop = homeBinding.stop
        var isstop = true
        ruta()
        stop.setOnClickListener {
            video.stopPlayback()
            video.seekTo(0)
            pause.visibility = View.INVISIBLE
            play.visibility = View.VISIBLE
            isstop = true
        }
        play.setOnClickListener {
            if (isstop) {
                video.setVideoURI(Uri.parse(ruta()))
                isstop = false
                video.start()
            } else {
                video.start()
            }
            play.visibility = View.INVISIBLE
            pause.visibility = View.VISIBLE

        }
        pause.setOnClickListener {
            video.pause()
            pause.visibility = View.INVISIBLE
            play.visibility = View.VISIBLE
        }
    }

    private fun  ruta(): String = "android.resource://" + activity?.packageName + "/" + R.raw.video


}