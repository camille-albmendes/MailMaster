package br.com.fiap.mailmaster.dao

import br.com.fiap.mailmaster.filter.FiltroEmail
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.repository.todosOsEmails

fun getEmailsByFiltro(filtro: FiltroEmail): List<Email> {
    return todosOsEmails.filter { email ->
        filtro.matchEmail(email)
    }
}

fun getEmailById(id: Long): Email? {
    val filtroComId = FiltroEmail(id)
    return todosOsEmails.find { email ->
        filtroComId.matchEmail(email)
    }
}
