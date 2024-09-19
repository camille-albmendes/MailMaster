package br.com.fiap.mailmaster.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.repository.MockEmailService

@Composable
fun EmailListScreen(navController: NavController) {
    val emailService = MockEmailService()
    val emails = emailService.listEmails()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(emails) { email ->
            EmailItem(email = email) {
                // Ao clicar em um e-mail, navegue para a tela de detalhes
                navController.navigate("emailDetails/${email.id}")
            }
        }
    }
}

@Composable
fun EmailItem(email: Email, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onClick() }) {
        Text(text = "Assunto: ${email.subject}")
        Text(text = "De: ${email.sender}")
        Text(text = "Para: ${email.recipient}")
        Text(text = "Mensagem: ${email.body}")
    }
}
