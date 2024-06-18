package br.com.fiap.mailmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.mailmaster.filter.FiltroEmail
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.repository.buscarEmails
import br.com.fiap.mailmaster.repository.filtrarEmails
import br.com.fiap.mailmaster.screens.ReadScreen

class ReadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadScreen(email)
        }
    }

    companion object {
        var email: Email? = null
    }
}
