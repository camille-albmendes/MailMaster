package br.com.fiap.mailmaster.repository

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun setTheme(theme: String) {
        sharedPreferences.edit().putString("theme", theme).apply()
    }

    fun getTheme(): String? {
        return sharedPreferences.getString("theme", "default") // Valor padrão "default"
    }

    fun setColor(color: String) {
        sharedPreferences.edit().putString("color", color).apply()
    }

    fun getColor(): String? {
        return sharedPreferences.getString("color", "blue") // Valor padrão "blue"
    }

    fun setCategory(category: String) {
        sharedPreferences.edit().putString("category", category).apply()
    }

    fun getCategory(): String? {
        return sharedPreferences.getString("category", "general") // Valor padrão "general"
    }

    fun setLabel(label: String) {
        sharedPreferences.edit().putString("label", label).apply()
    }

    fun getLabel(): String? {
        return sharedPreferences.getString("label", "label1") // Valor padrão "label1"
    }

    // Adicione outras preferências conforme necessário
}
