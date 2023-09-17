package th.ac.tu.dome.guessinggame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random.Default.nextInt
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var countTextView: TextView
    lateinit var editText: EditText
    lateinit var buttonPlayAgain: Button
    lateinit var imageButtonCheck: ImageButton
    lateinit var hintTextView: TextView

    var random: Int = nextInt(1,100)
    var count: Int = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Number Guessing Game"


        textView = findViewById(R.id.textView)
        countTextView = findViewById(R.id.countTextView)
        editText = findViewById(R.id.editText)
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain)
        imageButtonCheck = findViewById(R.id.imageButtonCheck)
        hintTextView = findViewById(R.id.hintTextView)

        textView.text = "Try to guess the number i'm thinking of from 1-100"
        countTextView.text = "Guesses: $count"

        imageButtonCheck.setOnClickListener {
            performCheck()
        }
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performCheck()
                return@setOnEditorActionListener true
            }
            false
        }
        buttonPlayAgain.setOnClickListener {
            reset()
        }
    }
    fun reset(){
        random = nextInt(1,100)
        count = 0
        hintTextView.text = ""
        textView.text = "Try to guess the number i'm thinking of from 1-100"
        countTextView.text = "Guesses: $count"
        editText.text.clear()
    }
    private fun performCheck() {
        val number: Int = editText.text.toString().toInt()
        count += 1
        countTextView.text = "Guesses: $count"

        if (number < random) {
            hintTextView.text = "Hint: The answer is higher than your guess!️"
            editText.text.clear()
        } else if (number > random) {
            hintTextView.text = "Hint: The answer is lower than your guess!"
            editText.text.clear()
        } else {
            hintTextView.text = "Congratulations! You guessed correctly in $count times!"
            editText.text.clear()
        }
    }

}