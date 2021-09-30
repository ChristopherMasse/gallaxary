package com.christophermasse.spaceshowcase.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.christophermasse.spaceshowcase.ApiKey
import com.christophermasse.spaceshowcase.R
import com.christophermasse.spaceshowcase.databinding.ActivitySplashBinding
import com.christophermasse.spaceshowcase.ui.TabActivity
import com.christophermasse.spaceshowcase.ui.signup.SignupActivity
import timber.log.Timber

class SplashActivity: AppCompatActivity() {


    private lateinit var handler: Handler

    private lateinit var runnable: Runnable

    private val minSplashTime = 2000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SplashTheme)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("Splash")

        handler = Handler(Looper.getMainLooper())

        runnable = Runnable {
            var i: Intent;
            if (ApiKey.API_KEY.equals("enter_your_api_key_here")){
                i = Intent(this, SignupActivity::class.java)
            } else{
                i = Intent(this, TabActivity::class.java)
            }
            startActivity(i)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        handler.postDelayed(runnable, minSplashTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (handler!= null && runnable != null){
            handler.removeCallbacks(runnable)
        }
    }
}