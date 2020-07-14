package com.example.eziketobenna.bakingapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.eziketobenna.bakingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * Using [Provider] to enable lazy retrieval of the [NavController]
     * The id used to get the [NavController] instance will be looked up
     * before the call to setContentView cos in the [Hilt_MainActivity],
     * inject() is called before super.onCreate(), which is normal.
     *
     * Using [Provider] or [Lazy] will prevent it from throwing an error,
     * since they offer lazy retrieval and or initialization.
     */
    @Inject
    lateinit var navControllerProvider: Provider<NavController>

    private val navController: NavController
        get() = navControllerProvider.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            AppBarConfiguration(navController.graph)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!onSupportNavigateUp()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
