package br.com.fiap.mailmaster.repository

import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.model.RemetenteGenerico

private val remetenteEmailUm = RemetenteGenerico("FIAP", "financeiro@fiap.com.br")
private val remetenteEmailDois = RemetenteGenerico("Ifood", "cupons@ifood.com.br")

val emailUm = Email(1L, "Pagamento pendente - FIAP", "Pague. Apenas pague", remetenteEmailUm)
val emailDois = Email(2L, "Cupom gratuito", "Você ganhou um cupom", remetenteEmailDois)

val todosOsEmails = listOf(
    emailUm,
    emailDois
)
