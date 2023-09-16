package th.ac.tu.dome.guessinggame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random.Default.nextInt
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var imageButtontRefresh: ImageButton
    lateinit var  imageButtonCheck: ImageButton
    lateinit var hintTextView: TextView

    var random: Int = nextInt(1,100)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Number Guessing Game"


        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        imageButtontRefresh = findViewById(R.id.imageButtonRefresh)
        imageButtonCheck = findViewById(R.id.imageButtonCheck)
        hintTextView = findViewById(R.id.hintTextView)

        textView.text = "Try to guess the number i'm thinking of from 1-100"

        imageButtonCheck.setOnClickListener {
            val number: Int = editText.text.toString().toInt()

            if (number < random) {
                hintTextView.text =  "Hint: The answer is higher than your guess!️"
                editText.text.clear()

            }else if (number > random){
                hintTextView.text =  "Hint: The answer is lower than your guess!"
                editText.text.clear()

            }else {
                hintTextView.text =  "Congratulations! You guessed correctly! "
                editText.text.clear()

            }
        }
        imageButtontRefresh.setOnClickListener {
            reset()
        }
    }
    fun reset(){
        random = nextInt(1,100)
        hintTextView.text = ""
        textView.text = "Try to guess the number i'm thinking of from 1-100"
        editText.text.clear()
    }
}