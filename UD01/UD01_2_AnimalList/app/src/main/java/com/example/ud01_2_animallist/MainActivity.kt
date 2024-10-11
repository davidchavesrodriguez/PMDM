package com.example.ud01_2_animallist

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
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

        val buttonSend = findViewById<Button>(R.id.buttonSend)
        buttonSend.setOnClickListener {
            val spinnerAnimalType = findViewById<Spinner>(R.id.listAnimals)
            val textAnimalKind = findViewById<TextView>(R.id.textAnimalKind)
            textAnimalKind.text = getAnimalKind(spinnerAnimalType.selectedItemId).joinToString("\n")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getAnimalKind(id: Long): List<String> = when (id) {
        0L -> resources.getStringArray(R.array.dogBreeds).toList()
        1L -> resources.getStringArray(R.array.catBreeds).toList()
        2L -> resources.getStringArray(R.array.duckBreeds).toList()
        else -> listOf()
    }
}
