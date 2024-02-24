package com.example.penis

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.Toast
import com.example.penis.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_main2)

        val imgButton = findViewById<ImageButton>(R.id.imgButton)
        imgButton.setOnClickListener {
            val intent = Intent(binding.root.context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

