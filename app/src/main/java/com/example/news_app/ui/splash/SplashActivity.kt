package com.example.news_app.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.news_app.ui.home.HomeActivity
import com.example.news_app.R
import com.example.news_app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=DataBindingUtil.setContentView(this,R.layout.activity_splash)
        Handler(Looper.getMainLooper())
            .postDelayed({
                Startactivity()
            },2000)

    }
    lateinit var viewBinding:ActivitySplashBinding

    private fun Startactivity() {
          val intent =Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}