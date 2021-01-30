package com.hasan.rnumberfromfservice.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hasan.rnumberfromfservice.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
