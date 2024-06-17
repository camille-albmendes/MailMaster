package br.com.fiap.mailmaster.ui.components.emailDetalhe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.service.addEvent

@Composable
fun EmailBody(email: Email) {
    val ctx = LocalContext.current
    Column {
        Text(text = "Corpo do email")
        Text(text = "UsuarioFiap522646842845822@gmail.com")
        Text(text = "/^3E8c~6M2m469pK2)]52Yx6n6g9692S")

        Button(onClick = {
            addEvent(ctx, email.assunto)
        }) {
            Text(text = "Adicionar ao calendário")
        }
    }

}