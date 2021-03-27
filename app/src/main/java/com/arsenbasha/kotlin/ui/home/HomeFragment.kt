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
        val path = "android.resource://" + activity?.packageName + "/" + R.raw.video
        val video =homeBinding.video
        video.setVideoURI(Uri.parse(path))
        val play=homeBinding.play
        val pause=homeBinding.pause
        play.setOnClickListener { video.start()}
        pause.setOnClickListener { video.pause()}
    }

}