package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.ComposeTheme
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.size

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceRollerApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp(modifier: Modifier = Modifier) {
    DiceWithButtonAndImage(modifier = modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    val result1= remember{ mutableStateOf(0) }
    val result2= remember{ mutableStateOf(0) }
    val hasRolled = remember{ mutableStateOf(false)}

    val imageResource1 = when(result1.value){
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    val imageResource2 = when(result2.value){
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Box(modifier = modifier.fillMaxSize()){
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) { Row() {
            Image(
                painter = painterResource(imageResource1),
                contentDescription = result1.value.toString(),
                modifier = Modifier.size(150.dp)
            )
            Image(
                painter = painterResource(imageResource2),
                contentDescription = result2.value.toString(),
                modifier = Modifier.size(150.dp)
            ) }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                result1.value = (1..6).random()
                result2.value = (1..6).random()
                hasRolled.value = true
            }
            ) {
                Text(stringResource(R.string.roll))
            }
        }
            if (hasRolled.value){
                Card(
                    colors = CardDefaults.cardColors(containerColor=Color.Gray),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom=50.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Text(
                        text = if (result1.value == result2.value)
                            stringResource(R.string.lucky_message)
                        else stringResource(R.string.unlucky_message),
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
    }
}