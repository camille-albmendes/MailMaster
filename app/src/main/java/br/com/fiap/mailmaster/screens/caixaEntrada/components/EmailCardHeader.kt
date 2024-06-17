package br.com.fiap.mailmaster.screens.caixaEntrada.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import br.com.fiap.mailmaster.dao.getEmailsByFiltro
import br.com.fiap.mailmaster.filter.FiltroEmail

@Composable
fun EmailCardHeader() {
    var busca by remember { mutableStateOf(TextFieldValue("")) }

    var filtroEmail = FiltroEmail()

    return Column {
        Text(text = busca.text)
        TextField(
            value = busca,
            onValueChange = { newText ->
                busca = newText
                filtroEmail.busca = newText.text
                filtrarEmails(filtroEmail)
            }
        )

        Button(onClick = {
            filtroEmail.limparTudoMenosBusca()
            filtrarEmails(filtroEmail)
        }) {
            Text(text = "Tudo")
        }

        Button(onClick = { /*TODO - mostrar lista de categorias*/ }) {
            Text(text = "Categorias")
        }

        Button(onClick = {
            filtroEmail.limparTudoMenosBusca()
            filtroEmail.favorito = true
            filtrarEmails(filtroEmail)
        }) {
            Text(text = "Favoritos")
        }
    }
}

fun filtrarEmails(filtroEmail: FiltroEmail) {
    var emails = getEmailsByFiltro(filtroEmail)
    // TODO set estado da lista de emails
}
