package com.example.ud04_1_inbox

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge // Enables edge-to-edge support
import androidx.appcompat.app.AppCompatActivity // Import AppCompatActivity for backward compatibility
import androidx.core.view.ViewCompat // Import ViewCompat for working with views and their properties
import androidx.core.view.WindowInsetsCompat // Import WindowInsetsCompat to manage window insets
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar // Import MaterialToolbar from the Material Design library

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call the parent method for initialization
        enableEdgeToEdge() // Activates edge-to-edge mode
        setContentView(R.layout.activity_main) // Set the layout for the activity

        // Find the MaterialToolbar in the layout and set it as the ActionBar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.container_fragments) as NavHostFragment
        val navController= navHostFragment.navController
        toolbar.setupWithNavController(navController)


        // Set a listener to apply window insets to the main view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Get the insets for system edges (like the status bar and navigation bar)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Adjust the padding of the main view to prevent content from being hidden behind system edges
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Return the processed insets
        }
    }

    // Method to inflate the options menu in the Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu) // Inflate the menu defined in XML
        return super.onCreateOptionsMenu(menu) // Call the parent method
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController= findNavController(R.id.container_fragments)
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item)
    }
}
