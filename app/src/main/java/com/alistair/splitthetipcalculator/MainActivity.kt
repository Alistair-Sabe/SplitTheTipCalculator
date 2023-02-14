package com.alistair.splitthetipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alistair.splitthetipcalculator.components.InputField
import com.alistair.splitthetipcalculator.ui.theme.SplitTheTipCalculatorTheme
import com.alistair.splitthetipcalculator.widgets.RoundButtonIcon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                //TopHeader()
                MainContent()
            }
    
        }
    }
}
@Composable
fun MyApp(content: @Composable () -> Unit) {

    SplitTheTipCalculatorTheme {
    // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()

        }
    }
}

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

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainContent(){
    BillForm() { billAmt ->
        Log.d("AMT", "MainContent: $billAmt"

        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier,
                onValChange: (String) -> Unit = {})
    {
        val totalBillState = remember {
            mutableStateOf("")
        }
        val validState = remember(totalBillState.value) {
            totalBillState.value.trim().isNotEmpty()
        }
        val keyboardController = LocalSoftwareKeyboardController.current

        val sliderPositionState =  remember {
            mutableStateOf(0f)
        }

        Surface(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, Color.LightGray)

        ) {
            Column(modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {

                InputField(
                    valueState = totalBillState,
                    labelId = "Enter Bill Total",
                    enabled = true,
                    isSingleLine = true,
                    onAction = KeyboardActions {
                        if (!validState) return@KeyboardActions
                        //Todo - onValueChanged
                        onValChange(totalBillState.value.trim())

                        keyboardController?.hide()

                    })

                //f (validState) {
                     Row(modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start) {
                        Text(text = "Split",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically))

                        Spacer(modifier = Modifier.width(120.dp))

                        Row(modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End) {

                            //minus button
                            RoundButtonIcon(
                                imageVector = Icons.Default.Remove,
                                onClick = { /*TODO*/ Log.d("Icon", "Subtract")})

                            //Text

                            Text(text = "1",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 9.dp, end = 9.dp))

                            //plus button
                            RoundButtonIcon(
                                imageVector = Icons.Default.Add,
                                onClick = { /*TODO*/ Log.d("Icon", "Add")})


                        }
                    }

                //Tip row
                Row (modifier = Modifier.padding(horizontal = 3.dp
                    , vertical = 12.dp)
                ){
                    Text(text = "Tip",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(200.dp))

                    Text(text = "$10.00",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically))
                }

                Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "10%")

                    Spacer(modifier = Modifier.height(15.dp))

                //Slider
                Slider(value = sliderPositionState.value,
                    onValueChange = { newVal ->
                        sliderPositionState.value = newVal
                        Log.d("Slider", "BillForm: $newVal")
                    })

                }
//                }else {
//                    Box() {
//
//                    }
//                }


            }

        }
    }

