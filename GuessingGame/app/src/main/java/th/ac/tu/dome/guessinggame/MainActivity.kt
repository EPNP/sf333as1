package th.ac.tu.dome.guessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import th.ac.tu.dome.guessinggame.ui.theme.GuessinggameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NumberGuessingGame()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberGuessingGame() {
    var random by remember { mutableStateOf(Random.nextInt(1, 1000)) }
    var count by remember { mutableStateOf(0) }
    var inputText by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("") }

    val handleCheckButtonClick: () -> Unit = {
        val number = inputText.toIntOrNull()
        if (number != null) {
            count++
            if (number < random) {
                hint = "Hint: The answer is higher than your guess!"
            } else if (number > random) {
                hint = "Hint: The answer is lower than your guess!"
            } else {
                hint = "Congratulations! You guessed correctly in $count times!"
            }
            inputText = ""
        }
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopAppBar(
                title = { Text(text = "Number Guessing Game") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Try to guess the number I'm thinking of from 1-1000",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                BasicTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    textStyle = TextStyle(color = Color(0xFF9D76C1)),
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            handleCheckButtonClick()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp)
                        .border(1.dp, Color(0xFF9D76C1), RoundedCornerShape(4.dp))
                        .padding(8.dp)
                )
                Text(text = hint, color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))
                Text(
                    text = "Guesses: $count",
                    modifier = Modifier.padding(bottom = 32.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            handleCheckButtonClick()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Check")
                    }
                    Button(
                        onClick = {
                            random = Random.nextInt(1, 1000)
                            count = 0
                            hint = ""
                            inputText = ""
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Play Again")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNumberGuessingGame() {
    NumberGuessingGame()
}
