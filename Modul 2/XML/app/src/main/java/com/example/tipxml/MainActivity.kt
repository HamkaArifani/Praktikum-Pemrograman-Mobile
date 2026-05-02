package com.example.tipxml

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tipxml.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        tipCalculatorApp(binding)
    }
}
private fun tipCalculatorApp(binding: ActivityMainBinding){
    billField(binding){
        calculateAndDisplay(binding)
    }
    tipDropDown(binding){
        calculateAndDisplay(binding)
    }
    roundUpSwitch(binding){
        calculateAndDisplay(binding)
    }
}

private fun billField(binding: ActivityMainBinding, onValueChange : (String)-> Unit){
    binding.billEditText.doAfterTextChanged { onValueChange(it.toString()) }
}

private fun tipDropDown(binding: ActivityMainBinding, onPercentageChange: (Double)->Unit){
    val options = listOf(15.0, 18.0, 20.0)
    val optionsUi = options.map { "${it.toInt()}%" }
    val adapter = ArrayAdapter(
        binding.root.context,
        android.R.layout.simple_list_item_1,
        optionsUi
    )

    binding.tipDropDownText.setAdapter(adapter)
    binding.tipDropDownText.setText(optionsUi[0], false)
    binding.tipDropDownText.setOnItemClickListener{_, _, position, _ ->
        val selectedOptions = options[position]
        onPercentageChange(selectedOptions)
    }
}
private fun roundUpSwitch(binding: ActivityMainBinding,onSwitchedChange: (Boolean)-> Unit){
    binding.roundUpSwitch.setOnCheckedChangeListener { _, isChecked ->
        onSwitchedChange(isChecked)
    }
}

private fun calculateAndDisplay(binding: ActivityMainBinding){
    val amountInput = binding.billEditText.text.toString()
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    val percentageText = binding.tipDropDownText.text.toString().replace("%", "")
    val percentage = percentageText.toDoubleOrNull() ?: 15.00

    val isRounded = binding.roundUpSwitch.isChecked

    val tipResult = calculateTip(amount, percentage, isRounded)
    binding.tipResultText.text = binding.root.context.getString(R.string.tip_amount, tipResult)

}

private fun calculateTip(amount : Double, tipPercent : Double, roundUp : Boolean): String{
    val tip = tipPercent / 100 * amount
    val finalTip = if (roundUp) ceil(tip) else tip
    return NumberFormat.getCurrencyInstance().format(finalTip)
}