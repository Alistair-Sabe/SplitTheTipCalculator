package com.alistair.splitthetipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alistair.splitthetipcalculator.ui.theme.SplitTheTipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp {
                TopHeader()
            }
    
        }
    }
}
@Composable
fun myApp(content: @Composable () -> Unit) {

    SplitTheTipCalculatorTheme {
    // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()

        }
    }
}

@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 100.0) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),

        color = Color(0xFF90EE90)
        ) {
        Column(modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
            //formatting the parsed value
            val total = "%.2f".format(totalPerPerson)
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.h5)
            Text(text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold)

        }
        
    }
    
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SplitTheTipCalculatorTheme {
        myApp {
            Text("Tip Calculator")
        }

    }
}