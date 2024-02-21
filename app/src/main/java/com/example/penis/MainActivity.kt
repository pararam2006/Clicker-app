package com.example.penis

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.penis.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val df: SimpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
        var counter: Int = 0
        var lastCounter: Int = 0

        //Кнопка плюса
        binding.buttonPlus.setOnClickListener {
            ++counter
            binding.time.text = df.format(Date())
            binding.counterView.text = "Кол-во нажатий: $counter"

            //Вывод Toast при 50, 100, 150 и т.д. нажатиях
            when(counter) {
                lastCounter + 20 -> {
                    Toast.makeText(this, "$counter нажатий! Так держать!", Toast.LENGTH_SHORT).show()
                    lastCounter += 20
                    if (counter == 100) {
                        Toast.makeText(this, "$counter нажатий! Так держать!", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "А теперь иди и поиграй во что-то нормальное ;)", Toast.LENGTH_LONG).show()
                    }
                }

                1 -> Toast.makeText(this, "На 100 нажатий будет сюрприз ;D", Toast.LENGTH_LONG).show()

            }
        }
        //Кнопка минуса
        binding.buttonMinus.setOnClickListener {
            if(counter != 0) {
                --counter
                binding.time.text = df.format(Date())
                binding.counterView.text = "Кол-во нажатий: $counter"
//            Toast.makeText(this, "Press button to update data", Toast.LENGTH_SHORT).show()
            }
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

    }
}