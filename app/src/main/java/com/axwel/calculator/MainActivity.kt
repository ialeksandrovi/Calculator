package com.axwel.calculator

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.axwel.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
}