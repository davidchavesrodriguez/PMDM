package com.example.ud06_1_chronosbinding

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ud06_1_chronosbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val RUNNING_KEY = "running"
    val OFFSET_KEY = "offset"
    val BASE_KEY = "base"
    lateinit var binding: ActivityMainBinding

    private var running = false
    private var offset = 0L

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(OFFSET_KEY, offset)
        outState.putLong(BASE_KEY, binding.chronometerMain.base)
        super.onSaveInstanceState(outState)
    }

    // onCreate: Initialize the activity and set up the user interface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Chronometer with ViewBinding
        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running) {
                binding.chronometerMain.base = savedInstanceState.getLong(BASE_KEY)
                binding.chronometerMain.start()
            } else {
                binding.chronometerMain.base = SystemClock.elapsedRealtime() - offset
            }
        }

        // Button click listeners using ViewBinding
        binding.buttonStart.setOnClickListener {
            if (!running) {
                binding.chronometerMain.base = SystemClock.elapsedRealtime() - offset
                binding.chronometerMain.start()
                running = true
            }
        }

        binding.buttonPause.setOnClickListener {
            if (running) {
                offset = SystemClock.elapsedRealtime() - binding.chronometerMain.base
                binding.chronometerMain.stop()
                running = false
            }
        }

        binding.buttonRestart.setOnClickListener {
            if (running) {
                binding.chronometerMain.stop()
                running = false
            }
            binding.chronometerMain.base = SystemClock.elapsedRealtime()
            offset = 0L
        }
    }

    // onStop: Save the current state of the chronometer if the activity is going to the background
    override fun onStop() {
        if (running) {
            offset = SystemClock.elapsedRealtime() - binding.chronometerMain.base
            binding.chronometerMain.stop()
        }
        super.onStop()
    }

    // onRestart: Restore the state of the chronometer when coming back from the background
    override fun onRestart() {
        if (running) {
            binding.chronometerMain.base = SystemClock.elapsedRealtime() - offset
            binding.chronometerMain.start()
        }
        super.onRestart()
    }

    // onPause: Stop the chronometer if the activity is no longer in focus
    override fun onPause() {
        if (running) {
            offset = SystemClock.elapsedRealtime() - binding.chronometerMain.base
            binding.chronometerMain.stop()
        }
        super.onPause()
    }

    // onResume: Restart the chronometer if the activity is coming back into focus
    override fun onResume() {
        if (running) {
            binding.chronometerMain.base = SystemClock.elapsedRealtime() - offset
            binding.chronometerMain.start()
        }
        super.onResume()
    }
}
