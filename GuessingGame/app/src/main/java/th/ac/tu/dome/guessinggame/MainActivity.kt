package th.ac.tu.dome.guessinggame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var imageButtontRefresh: ImageButton
    lateinit var  imageButtonCheck: ImageButton

    var random: Int = nextInt(1,100)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        imageButtontRefresh = findViewById(R.id.imageButtonRefresh)
        imageButtonCheck = findViewById(R.id.imageButtonCheck)

        textView.text = "Try to guess the number i'm thinking of from 1-100"

        imageButtonCheck.setOnClickListener {
            val number: Int = editText.text.toString().toInt()

            if (number < random) {
                textView.text = "Hint: It's Lower!"
                editText.text.clear()

            }else if (number > random){
                textView.text = "Hint: It's Higher!"
                editText.text.clear()

            }else {
                textView.text = "Congratulation, your number is right!!! "
                editText.text.clear()

            }
        }
        imageButtontRefresh.setOnClickListener {
            reset()
        }
    }
    fun reset(){
        random = nextInt(1,100)
        textView.text = "Try to guess the number i'm thinking of from 1-100"
        editText.text.clear()
    }
}