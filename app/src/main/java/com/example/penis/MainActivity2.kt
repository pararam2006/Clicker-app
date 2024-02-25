package com.example.penis

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.penis.databinding.ActivityMain2Binding
import com.example.penis.databinding.ActivityMain2Binding.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgButton.setOnClickListener {
            val intent = Intent(binding.root.context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

