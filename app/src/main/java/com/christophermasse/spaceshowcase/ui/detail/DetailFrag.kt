package com.christophermasse.spaceshowcase.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.christophermasse.spaceshowcase.databinding.FragDetailBinding

class DetailFrag:Fragment() {

    lateinit var binding: FragDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragDetailBinding.inflate(inflater, container, false)
        
        return binding.root


    }
}