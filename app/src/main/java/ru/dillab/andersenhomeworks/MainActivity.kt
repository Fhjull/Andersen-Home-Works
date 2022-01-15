package ru.dillab.andersenhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Getting a reference to the nav_host_fragment and assign it to the navController property
        // This is needed to construct our navigation as we specified in nav_graph.xml
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Adds up button (back button) in title (top left corner)
        setupActionBarWithNavController(navController)
    }

    // This method allows to handle the up button.
    // Needed with setupActionBarWithNavController(navController)
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}