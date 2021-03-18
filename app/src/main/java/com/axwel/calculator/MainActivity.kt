package com.axwel.calculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.axwel.calculator.databinding.ActivityMainBinding

const val KEY_DEFAULT_THEME = "DEFAULT_THEME"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDefaultTheme = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (isDefaultTheme) {
            setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar)
        } else {
            setTheme(R.style.Theme_AppCompat_DayNight)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainer, CalculatorFragment.newInstance(), CalculatorFragment.TAG)
            .commit()

    }

    override fun onCreateOptionsMenu(toolBarMenu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, toolBarMenu)
        return super.onCreateOptionsMenu(toolBarMenu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        isDefaultTheme = intent.getBooleanExtra(KEY_DEFAULT_THEME, true)
    }
}