package ru.urban.android_hiddencalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstEditTextET: EditText
    private lateinit var secondEditTextET: EditText

    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button
    private lateinit var buttonMultBTN: Button
    private lateinit var buttonDivBTN: Button

    private lateinit var sendDataBTN: Button

    private lateinit var resultTV: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firstEditTextET = findViewById(R.id.firstEditTextET)
        secondEditTextET = findViewById(R.id.secondEditTextET)

        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDifBTN = findViewById(R.id.buttonDifBTN)
        buttonMultBTN = findViewById(R.id.buttonMultBTN)
        buttonDivBTN = findViewById(R.id.buttonDivBTN)

        sendDataBTN = findViewById(R.id.sendDataBTN)

        resultTV = findViewById(R.id.resultTV)

        buttonSumBTN.setOnClickListener(this)
        buttonDifBTN.setOnClickListener(this)
        buttonMultBTN.setOnClickListener(this)
        buttonDivBTN.setOnClickListener(this)

        sendDataBTN.setOnClickListener { view ->
            if(resultTV.text.isEmpty())
                return@setOnClickListener
            val intent = Intent()
            intent.putExtra("result", resultTV.text.toString())
            setResult(RESULT_OK, intent)
            Toast.makeText(this, "Результат отправлен", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onClick(p0: View) {
        if (firstEditTextET.text.isEmpty() || secondEditTextET.text.isEmpty()){
            return
        }

        var first = firstEditTextET.text.toString().toDouble()
        var second = secondEditTextET.text.toString().toDouble()

        var result = when (p0.id){
            R.id.buttonSumBTN -> Operation(first, second).sum()
            R.id.buttonDifBTN -> Operation(first, second).dif()
            R.id.buttonMultBTN -> Operation(first, second).mult()
            R.id.buttonDivBTN -> Operation(first, second).div()
            else -> 0.0
        }

        resultTV.text = result.toString()
    }
}