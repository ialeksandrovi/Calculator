package com.axwel.calculator


import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axwel.calculator.databinding.ActivitySettingsBinding

class SettingsActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        actionBar?.title = getString(R.string.settings)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)

        val preferences = getSharedPreferences(this.packageName, MODE_PRIVATE)
        val editor = preferences.edit()


        binding.tvTheme.text = getString(R.string.choose_theme)
        binding.sDefaultTheme.isEnabled = preferences.getBoolean("isDefaultTheme", true)
        binding.btnSave.background = getDrawable(R.drawable.save_button_border)
        binding.btnSave.setOnClickListener {
            editor.putBoolean("isDefaultTheme", binding.sDefaultTheme.isEnabled)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(KEY_DEFAULT_THEME, binding.sDefaultTheme.isEnabled)
            }
            startActivity(intent)
        }

    }
}