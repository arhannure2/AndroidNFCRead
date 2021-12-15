package com.example.anroidnfcread

/*
Created by
Hannure Abdulrahim


on 14/Dec/2021.
 */


import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidkeypairgenerator.startNewActivity
import com.example.anroidnfcread.MainActivity
import com.example.anroidnfcread.R


class FirstScreenActivity : AppCompatActivity() {

    val SPLASH_TIME_OUT: Long = 3000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        headerTopColor(ContextCompat.getColor(applicationContext, R.color.purple_200))


        setContentView(R.layout.activity_splash)

        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                ///////Your Code
                //go to
                startNewActivity(MainActivity::class.java)
                ///finish this activity
                finish()

            }, SPLASH_TIME_OUT)
        }

    }


    public override fun onResume() {
        super.onResume()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR

    }

    public override fun onPause() {
        super.onPause()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

    fun headerTopColor(COLOR_CODE: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = COLOR_CODE // top
            //            window.setNavigationBarColor(Color.TRANSPARENT); // bottom
        }
    }

}
