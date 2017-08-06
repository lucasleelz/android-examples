package com.lucaslz.geoquizforkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    val questionBank = listOf<Question>(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, false),
            Question(R.string.question_mideast, true),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, false),
            Question(R.string.question_asia, true)
    )

    var currentQuestionIndex = 0

    private var questionTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById<TextView>(R.id.question_text_view)
        val trueButton = findViewById<Button>(R.id.true_button)
        val falseButton = findViewById<Button>(R.id.false_button)
        val previousImageButton = findViewById<ImageButton>(R.id.previous_image_button)
        val cheatButton = findViewById<Button>(R.id.cheat_button)
        val nextImageButton = findViewById<ImageButton>(R.id.next_image_button)

        updateQuestionTextView()
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }
    }

    private fun updateQuestionTextView() {
        val question = questionBank[currentQuestionIndex]
        questionTextView!!.setText(question.textResId)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val currentQuestion = questionBank[currentQuestionIndex]
        var messageResId = R.string.incorrect_toast
        if (userPressedTrue == currentQuestion.answerTrue) {
            messageResId = R.string.correct_toast
        }
        Toast.makeText(this@QuizActivity, messageResId, Toast.LENGTH_SHORT).show()
    }
}
