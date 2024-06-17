package br.com.fiap.mailmaster.Screens

import androidx.compose.runtime.Composable
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.ui.components.emailDetalhe.EmailBody

@Composable
fun EmailDetalheScreen() {
    return EmailBody(Email(null, "Assunto do email", "Conteúdo simples"))
}