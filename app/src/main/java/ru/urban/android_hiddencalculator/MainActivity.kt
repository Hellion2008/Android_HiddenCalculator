package ru.urban.android_hiddencalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var calculatorBTN: Button

    private lateinit var resultTextViewTV: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calculatorBTN = findViewById(R.id.calculatorBTN)
        resultTextViewTV = findViewById(R.id.resultTextView)

        calculatorBTN.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, CalculatorActivity::class.java)
        launchCalculation.launch(intent)
    }

    private val launchCalculation = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                val data = result.data
                resultTextViewTV.text = data!!.getStringExtra("result")
            }
        }
}