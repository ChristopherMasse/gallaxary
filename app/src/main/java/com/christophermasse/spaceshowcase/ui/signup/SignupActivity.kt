package com.christophermasse.spaceshowcase.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.christophermasse.spaceshowcase.R
import com.christophermasse.spaceshowcase.databinding.ActivitySignupBinding
import com.christophermasse.spaceshowcase.ui.TabActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}

