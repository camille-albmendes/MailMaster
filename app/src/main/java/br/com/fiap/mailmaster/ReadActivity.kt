package br.com.fiap.mailmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.mailmaster.screens.ReadScreen

class ReadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val emailId = intent.getStringExtra("emailId") ?: "Unknown Email"
        setContent {
            ReadScreen(emailId = emailId)
        }
    }
}
