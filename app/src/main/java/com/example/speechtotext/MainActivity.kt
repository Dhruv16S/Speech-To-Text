package com.example.speechtotext

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var listeningDot : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeningDot = findViewById(R.id.textDot1)

        ObjectAnimator.ofFloat(listeningDot, "translationY", 90f).apply {
            duration = 5000
            start()
        }

    }
}