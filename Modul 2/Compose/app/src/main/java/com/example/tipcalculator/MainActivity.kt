package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import java.text.NumberFormat
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.ceil
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Switch
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorApp(modifier: Modifier= Modifier){
    val amountInput = remember { mutableStateOf("") }
    val tipPercent = remember { mutableStateOf(15.0)}
    val roundUp = remember {mutableStateOf(false)}

    val amount = amountInput.value.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent.value, roundUp.value)

    Column(
        modifier=modifier
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Text(text = stringResource(R.string.calculate_tip))

        BillField(
            value = amountInput.value,
            onValueChange = {newValue -> amountInput.value = newValue}
        )
        TipDropdown(
            percentage = tipPercent.value,
            onPercentageChange = {newPercentage -> tipPercent.value = newPercentage}
        )
        RoundUpSwitch(
            clicked = roundUp.value,
            onClicked = {newStatus -> roundUp.value = newStatus}
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun BillField (
    modifier: Modifier = Modifier,
    value : String,
    onValueChange: (String) -> Unit
){
    TextField(
        value = value,
        leadingIcon = {
            Icon(
                painter= painterResource(R.drawable.money),
                contentDescription = null
            )
        },
        onValueChange = onValueChange,
        label = {Text(stringResource(R.string.bill_amount))},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipDropdown(
    modifier: Modifier = Modifier,
    percentage : Double,
    onPercentageChange : (Double)-> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val options = listOf(15.0, 18.0, 20.0)

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = {newValue->expanded.value=newValue},
        modifier = modifier
    ) {
        TextField(
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter= painterResource(R.drawable.percentage),
                    contentDescription = null
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
            },
            value = "${percentage.toInt()}%",
            onValueChange= {},
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor(type= MenuAnchorType.PrimaryNotEditable),
            label = {Text(stringResource(R.string.tip_percentage))}
        )
        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {expanded.value=false}
        ) {
            options.forEach {options->
                DropdownMenuItem(
                    text = {Text("${options.toInt()}%")},
                    onClick = {
                        onPercentageChange(options)
                        expanded.value=false
                    }
                )
            }
        }
    }
}

@Composable
fun RoundUpSwitch(
    modifier: Modifier = Modifier,
    clicked : Boolean,
    onClicked : (Boolean)->Unit
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(
            checked = clicked,
            onCheckedChange = onClicked
        )
    }
}

fun calculateTip(amount : Double, tipPercent : Double, roundUp : Boolean): String{
    val tip = tipPercent / 100 * amount
    val finalTip = if (roundUp) ceil(tip) else tip
    return NumberFormat.getCurrencyInstance().format(finalTip)
}