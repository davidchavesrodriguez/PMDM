package com.example.ud01_3_chronos

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val RUNNING_KEY = "running"
    val OFFSET_KEY = "offset"
    val BASE_KEY = "base"

    private lateinit var chronometer: Chronometer
    private var running = false
    private var offset = 0L
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(OFFSET_KEY, offset)
        outState.putLong(BASE_KEY, chronometer.base)
        super.onSaveInstanceState(outState)
    }

    // onCreate: Initialize the activity and set up the user interface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        chronometer = findViewById<Chronometer>(R.id.chronometerMain)

        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running) {
                chronometer.base = savedInstanceState.getLong(BASE_KEY)
                chronometer.start()
            } else {
                chronometer.base = SystemClock.elapsedRealtime() - offset
            }
        }

        // Button Functions: Set up click listeners for start, pause, and restart buttons
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        buttonStart.setOnClickListener {
            if (!running) {
                chronometer.base = SystemClock.elapsedRealtime() - offset
                chronometer.start()
                running = true
            }
        }

        val buttonPause = findViewById<Button>(R.id.buttonPause)
        buttonPause.setOnClickListener {
            if (running) {
                offset = SystemClock.elapsedRealtime() - chronometer.base
                chronometer.stop()
                running = false
            }
        }

        val buttonRestart = findViewById<Button>(R.id.buttonRestart)
        buttonRestart.setOnClickListener {
            if (running) {
                chronometer.stop()
                running = false
            }
            chronometer.base = SystemClock.elapsedRealtime()
            offset = 0L
        }
    }

    // onStop: Save the current state of the chronometer if the activity is going to the background
    override fun onStop() {
        if (running) {
            offset = SystemClock.elapsedRealtime() - chronometer.base
            chronometer.stop()
        }
        super.onStop()
    }

    // onRestart: Restore the state of the chronometer when coming back from the background
    override fun onRestart() {
        if (running) {
            chronometer.base = SystemClock.elapsedRealtime() - offset
            chronometer.start()
        }
        super.onRestart()
    }

    // onPause: Stop the chronometer if the activity is no longer in focus
    override fun onPause() {
        if (running) {
            offset = SystemClock.elapsedRealtime() - chronometer.base
            chronometer.stop()
        }
        super.onPause()
    }

    // onResume: Restart the chronometer if the activity is coming back into focus
    override fun onResume() {
        if (running) {
            chronometer.base = SystemClock.elapsedRealtime() - offset
            chronometer.start()
        }
        super.onResume()
    }
}