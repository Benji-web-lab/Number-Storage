package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {


        // UI Variables
      private lateinit var  editText: EditText
      private lateinit var button: Button
      private lateinit var buttonavg: Button
      private lateinit var textView: TextView


        // Data Variables
        val numbers = IntArray(10)
        private var counter = 0
        private var inputValue: String = ""
        private var parsedNumber: Int = 0


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

            // Link Variables to Views
            editText = findViewById(R.id.editInput)
            button = findViewById(R.id.btnAdd)
            buttonavg = findViewById(R.id.btnavg)
            textView    = findViewById(R.id.textmsg)

            //  Button Click Listener
            button.setOnClickListener {

                inputValue = editText.text.toString()

                if (counter < 10) {
                    if (inputValue.isNotEmpty()) {
                        parsedNumber        = inputValue.toInt()
                        numbers[counter]    = parsedNumber
                        counter++

                        textView.text = "Message: Saved $parsedNumber ($counter/10)"
                        editText.setText("")
                    } else {
                        textView.text = "Message: Please enter a number."
                    }
                } else {
                    textView.text = "Message: Array is full. Cannot store more values."
                }
            }
            //  Average Button
            buttonavg.setOnClickListener {
                if (counter == 0) {
                    textView.text = "Message: No numbers stored yet."
                } else {
                    val avg = calculateAverage()
                    textView.text = "Message: Average = $avg"
                }
            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    // ── Function: Calculate Average using While Loop ──────
    private fun calculateAverage(): Double {
        var sum = 0
        var i = 0

        while (i < counter) {       // while loop as shown on whiteboard
            sum += numbers[i]
            i++
        }

        return sum.toDouble() / counter
    }
}