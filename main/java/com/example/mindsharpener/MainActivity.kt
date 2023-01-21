package com.example.mindsharpener

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private var random = Random
    private var number1: Int = 0
    private var number2: Int = 0
    private var operator: Char = '+'
    private var point: Int = 0
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radiogroup)

        generateQuestion()
    }

    private fun generateQuestion() {

        val easyButton = findViewById<RadioButton>(R.id.eb)
        val mediumButton = findViewById<RadioButton>(R.id.mb)
        val hardButton = findViewById<RadioButton>(R.id.hb)
        val number1 = findViewById<TextView>(R.id.numb1)
        val number2 = findViewById<TextView>(R.id.numb2)

        var operatorNumber = random.nextInt(3)
        operator = when (operatorNumber) {
            0 -> '+'
            1 -> '-'
            2 -> '*'
            3 -> '/'
            else -> '+'
        }

        easyButton.setOnClickListener {
            val numbers = Random.nextInt(0, 9)
            val numbers1 = Random.nextInt(0, 9)
            operatorNumber = Random.nextInt(3).toChar().toInt()
            number1.text = numbers.toString()
            operator = operatorNumber.toChar()
            number2.text = numbers1.toString()
        }

        mediumButton.setOnClickListener {
            val numbers = Random.nextInt(0, 99)
            val numbers1 = Random.nextInt(0, 99)
            number1.text = numbers.toString()
            number2.text = numbers1.toString()
        }

        hardButton.setOnClickListener {
            val numbers = Random.nextInt(0, 999)
            val numbers1 = Random.nextInt(0, 999)
            number1.text = numbers.toString()
            number2.text = numbers1.toString()
        }
    }

    private fun checkAnswer() {
        val inputAnswer = findViewById<EditText>(R.id.answer)

        val correctAnswer = when (operator) {
            '+' -> number1 + number2
            '-' -> number1 - number2
            '*' -> number1 * number2
            '/' -> number1.toDouble() / number2
            else -> number1 + number2
        }

        if (inputAnswer == correctAnswer) {
            point++
            Toast.makeText(this, "Correct Answer!", Toast.LENGTH_SHORT).show()
        } else {
            point--
            Toast.makeText(this, "Incorrect Answer!", Toast.LENGTH_SHORT).show()
        }

        generateQuestion()
    }

    fun onSubmitButtonClick(view: View) {
        checkAnswer()
    }

}