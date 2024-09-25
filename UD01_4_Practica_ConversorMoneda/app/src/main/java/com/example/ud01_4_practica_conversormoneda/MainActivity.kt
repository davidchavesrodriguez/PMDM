package com.example.ud01_4_practica_conversormoneda

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var fieldDollar= findViewById<EditText>(R.id.fieldDollar)
        val fieldEuro= findViewById<TextView>(R.id.fieldEuro)

        val buttonConvert= findViewById<Button>(R.id.buttonConvert)
        buttonConvert.setOnClickListener {
            val valueDollar= fieldDollar.text.toString()
            fieldEuro.text = (valueDollar.toInt()*0.89).toString()
        }
    }
}