package com.example.wheater.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wheater.R

class Splach_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splach)

        val handler = Handler()

// إنشاء Runnable
        val runnable = object : Runnable {
            override fun run() {
val intent = Intent(baseContext,MainActivity::class.java)
                startActivity(intent)
            }
        }

handler.postDelayed(runnable,2000)
    }
}