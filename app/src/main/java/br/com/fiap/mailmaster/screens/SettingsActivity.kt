package br.com.fiap.mailmaster.screens

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.mailmaster.R
import br.com.fiap.mailmaster.repository.UserPreferences

class SettingsActivity : AppCompatActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        userPreferences = UserPreferences(this)

        val themeGroup = findViewById<RadioGroup>(R.id.theme_group)
        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.theme_light -> userPreferences.setTheme("light")
                R.id.theme_dark -> userPreferences.setTheme("dark")
            }
        }

        // Adicione código para outras preferências, como cores, categorias, etc.
    }
}
