package com.christophermasse.spaceshowcase.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.christophermasse.spaceshowcase.databinding.ActivityDetailBinding

class DetailActivity:AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}