package br.com.fiap.mailmaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.repository.MockEmailService

@Composable
fun EmailDetailsScreen(navController: NavController, emailId: String) {
    val emailService = MockEmailService()
    val email = emailService.getEmailDetails(emailId)

    if (email != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Assunto: ${email.subject}")  // Usando 'assunto' ao invés de 'subject'
            Text(text = "De: ${email.sender}")
            Text(text = "Para: ${email.recipient}")
            Text(text = "Mensagem: ${email.body}")  // Usando 'conteudo' ao invés de 'body'
        }
    } else {
        Text(text = "E-mail não encontrado")
    }
}
