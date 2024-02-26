package com.example.penis

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.penis.databinding.ActivityMainBinding
import java.util.Date
import java.util.Timer
import java.util.TimerTask

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var counter: Int = 0
    var lastCounter: Int = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Переменная для автонажатия
        val timer = Timer()

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val df = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")


        // Кнопка плюса через setOnClickListener
        /*binding.buttonPlus.setOnClickListener {
            ++counter
            binding.time.text = df.format(Date())
            binding.counterView.text = "Кол-во нажатий: $counter"

            when(counter) {
                lastCounter + 25 -> {
                    Toast.makeText(this, "$counter нажатий! Так держать!", Toast.LENGTH_SHORT).show()
                    lastCounter += 25
                    if (counter == 100) {
                        Toast.makeText(this, "А теперь иди и поиграй во что-то нормальное ;)", Toast.LENGTH_LONG).show()
                    }
                }

                1 -> Toast.makeText(this, "На 100 нажатий будет сюрприз ;D", Toast.LENGTH_LONG).show()

            }
        }*/


        binding.buttonPlus.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    val timer = Timer()

                    val task = object : TimerTask() {
                        override fun run() {
                            counter++
                            runOnUiThread {
                                binding.counterView.text = "Кол-во нажатий: $counter"
                            }
                        }
                    }
                    timer.scheduleAtFixedRate(task, 0, 700)
                    // Сохраняем ссылку на таймер в tag кнопки, чтобы можно было потом остановить
                    binding.buttonPlus.tag = timer

                }

                MotionEvent.ACTION_UP -> {
                    (binding.buttonPlus.tag as? Timer)?.cancel()
                    when(counter) {
                        lastCounter + 25 -> {
                            Toast.makeText(this, "$counter нажатий! Так держать!", Toast.LENGTH_SHORT).show()
                            lastCounter += 25
                            if (counter == 100) {
                                Toast.makeText(this, "А теперь иди и поиграй во что-то нормальное ;)", Toast.LENGTH_LONG).show()
                            }
                        }

                        1 -> Toast.makeText(this, "На 100 нажатий будет сюрприз ;D", Toast.LENGTH_LONG).show()

                    }
                }
            }
            true
        }
        //Кнопка минуса
        binding.buttonMinus.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    val timer = Timer()

                    val task = object : TimerTask() {
                        override fun run() {
                            if (counter == 0) {

                            } else {
                                counter--
                                runOnUiThread {
                                    binding.counterView.text = "Кол-во нажатий: $counter"
                                }
                            }
                            
                        }
                    }
                    timer.scheduleAtFixedRate(task, 0, 700)
                    // Сохраняем ссылку на таймер в tag кнопки, чтобы можно было потом остановить
                    binding.buttonMinus.tag = timer
                }

                MotionEvent.ACTION_UP -> {
                    (binding.buttonMinus.tag as? Timer)?.cancel()
                    when(counter) {
                        lastCounter + 25 -> {
                            Toast.makeText(this, "$counter нажатий! Так держать!", Toast.LENGTH_SHORT).show()
                            lastCounter += 25
                            if (counter == 100) {
                                Toast.makeText(this, "А теперь иди и поиграй во что-то нормальное ;)", Toast.LENGTH_LONG).show()
                            }
                        }

                        1 -> Toast.makeText(this, "На 100 нажатий будет сюрприз ;D", Toast.LENGTH_LONG).show()

                    }

                }
            }
            true
        }


        binding.buttonRefresh.setOnClickListener {
            if(counter > 0 && lastCounter >= 0) {
                counter = 0
                lastCounter = 0
                binding.counterView.text = "Кол-во нажатий: $counter"
                Toast.makeText(this, "Счетчик сброшен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Нечего сбрасывать", Toast.LENGTH_SHORT).show()
            }

        }

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }

        //TODO: "Сделать сохранение данных с помощью onSaveInstanceState и onRestoreInstanceState (или как-то по-другому)"
     override fun onSaveInstanceState(outState: Bundle) {
         outState.run {
            putInt("KEY", counter.toInt())

         }

         super.onSaveInstanceState(outState)
     }
     @SuppressLint("SetTextI18n")
     override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var counter = savedInstanceState.getInt("KEY")
        binding.counterView.text = "Кол-во нажатий: $counter"
     }

}