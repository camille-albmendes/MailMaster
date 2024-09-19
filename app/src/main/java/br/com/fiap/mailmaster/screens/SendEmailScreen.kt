package br.com.fiap.mailmaster.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.repository.MockEmailService

@Composable
fun SendEmailScreen() {
    val emailService = MockEmailService()

    // Aqui você pode criar campos de entrada de texto para coletar informações do e-mail
    // Para simplificação, vamos criar um e-mail mock diretamente

    val newEmail = Email(
        id = "3",
        sender = "you@example.com",
        recipient = "someone@example.com",
        subject = "Novo E-mail",
        body = "Este é um e-mail de teste.",
        timestamp = System.currentTimeMillis()
    )

    // Simular envio de e-mail
    val isSuccess = emailService.sendEmail(newEmail)

    if (isSuccess) {
        Text(text = "E-mail enviado com sucesso!")
    } else {
        Text(text = "Falha ao enviar o e-mail.")
    }
}
