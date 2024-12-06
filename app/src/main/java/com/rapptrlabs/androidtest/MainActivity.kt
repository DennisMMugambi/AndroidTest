package com.rapptrlabs.androidtest

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.rapptrlabs.androidtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main screen that lets you navigate to all other screens in the app.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    val callback = object : OnBackPressedCallback(
        true
    ) {
        override fun handleOnBackPressed() {
            if (navController.currentDestination?.id  == R.id.main) {
                showLogoutConfirmationDialog()
            } else {
                navController.navigateUp()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.activity_main_title)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        installSplashScreen()

        /**
         * =========================================================================================
         * INSTRUCTIONS
         * =========================================================================================
         *
         * 1. UI must work on Android phones of multiple sizes. Do not worry about Android Tablets.
         *
         * 2. Use this starter project as a base and build upon it. It is ok to remove some of the
         *    provided code if necessary.
         *
         * 3. Read the additional 'TODO' comments throughout the codebase, they will guide you.
         *
         * 3. Please take care of the bug(s) we left for you in the project as well.
         *
         * Thank you and Good luck. -  Rapptr Labs
         * =========================================================================================
         */

        // TODO: Make the UI look like it does in the mock-up
        // TODO: Add a ripple effect when the buttons are clicked

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.main)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        this.onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(resources.getString(R.string.confirm_logout))
        builder.setPositiveButton("Yes") { dialog, which ->
            finishAffinity()
            finish()
        }
        builder.setNegativeButton(
            resources.getString(R.string.no)
        ) { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}