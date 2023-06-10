package com.example.news_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.news_app.HomeActivity
import com.example.news_app.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper())
            .postDelayed({
                Startactivity()
            },2000)

    }

    private fun Startactivity() {
          val intent =Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}