package com.example.ud04_1_inbox

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge // Enables edge-to-edge support for more immersive UI
import androidx.appcompat.app.AppCompatActivity // Provides backward compatibility for older Android versions
import androidx.core.view.ViewCompat // Helps manage view compatibility for different API levels
import androidx.core.view.WindowInsetsCompat // Manages system windows like status bar and navigation bar
import androidx.drawerlayout.widget.DrawerLayout // Manages a layout that can slide in and out like a navigation drawer
import androidx.navigation.NavController // Controls navigation within the app's UI
import androidx.navigation.findNavController // Helps find the NavController from a fragment or view
import androidx.navigation.fragment.NavHostFragment // A host fragment for displaying destinations in navigation
import androidx.navigation.ui.AppBarConfiguration // Configures the behavior of the ActionBar with NavigationUI
import androidx.navigation.ui.NavigationUI // Links navigation actions with the UI components
import androidx.navigation.ui.setupWithNavController // Sets up UI components to follow the navigation system
import com.google.android.material.appbar.MaterialToolbar // Material Design component for a flexible toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView // Material Design component for bottom navigation bar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Calls the parent method to perform basic initialization
        enableEdgeToEdge() // Enables full-screen layout to allow content to display under system bars
        setContentView(R.layout.activity_main) // Sets the layout resource (activity_main.xml) as the content view

        // Top bar (Toolbar)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) // Sets the toolbar as the app's ActionBar for managing app navigation and menu

        // Navigation Controller
        // Retrieves the NavHostFragment from the layout and gets its NavController to handle navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_fragments) as NavHostFragment
        val navController =
            navHostFragment.navController // Gets the NavController from the NavHostFragment

        // Drawer Layout (for the navigation drawer)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // AppBarConfiguration to manage top-level destinations and handle up/back navigation
        var appBarConfiguration = AppBarConfiguration.Builder(navController.graph)

        // Navigation View
        val navigationView= findViewById<NavigationView>(R.id.nav_side)
        navigationView.setupWithNavController(navController)

        // Links the DrawerLayout to the AppBarConfiguration to enable hamburger menu and drawer behavior
        appBarConfiguration.setOpenableLayout(drawerLayout)
        val appBarBuild = appBarConfiguration.build() // Finalizes the configuration of the AppBar
        toolbar.setupWithNavController(
            navController,
            appBarBuild
        ) // Binds the toolbar with navigation controller

        // Bottom navigation bar
        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomBar)
        bottomBar.setupWithNavController(navController) // Sets up the bottom navigation with the NavController


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Inflates the options menu (e.g., overflow menu) into the Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            R.menu.menu_toolbar,
            menu
        ) // Inflates a menu from the XML resource file
        return super.onCreateOptionsMenu(menu) // Calls the parent method to complete the process
    }

    // Handles item selections from the options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Finds the NavController and delegates the item selection to the Navigation UI
        val navController = findNavController(R.id.container_fragments)
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item) // Calls the parent method for default behavior
    }
}
