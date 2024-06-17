package br.com.fiap.mailmaster.repository

import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.model.RemetenteGenerico
import com.google.firebase.database.FirebaseDatabase

private val remetenteEmailBoasVindas = RemetenteGenerico("MailMaster", "time@mailmaster.com.br")
private val remetenteEmailUm = RemetenteGenerico("FIAP", "financeiro@fiap.com.br")
private val remetenteEmailDois = RemetenteGenerico("Ifood", "cupons@ifood.com.br")
private val remetenteEmailTres = RemetenteGenerico("Samsung", "oferta@samsung.com.br")

val emailUm = Email(null, "Pagamento pendente - FIAP", "Pague. Apenas pague imediatamente", remetenteEmailUm)
val emailDois = Email(null, "Cupom gratuito", "Você ganhou um cupom. O código de uso é \"66-um-tapa-na-oreia\"", remetenteEmailDois)
val emailTres = Email(null, "Promoção samsung", "Nova promoção da samsung, pra você que cansou do travamento da motorola", remetenteEmailTres)

val todosOsEmails = listOf(
    emailUm,
    emailDois,
    emailTres
)

fun criarEmailBoasVindas(usuarioId: String) {
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val ref = firebaseDatabase.getReference()

    val emailBoasVindas = Email(
        ref
            .child("usuarios")
            .child(usuarioId)
            .child("emails")
            .push()
            .key,
        "Bem-vindo(a)",
        "Estamos felizes por você ter aceitado aceitar o nosso app. Aproveite o passeio!",
        remetenteEmailBoasVindas
    )

    ref
        .child("usuarios")
        .child(usuarioId)
        .child("emails")
        .child(emailBoasVindas.id!!)
        .setValue(emailBoasVindas)
}

fun criarEmailsMock(usuarioId: String) {
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val ref = firebaseDatabase.getReference()

    todosOsEmails.forEach {
        it.id = ref
            .child("usuarios")
            .child(usuarioId)
            .child("emails")
            .push()
            .key

        ref
            .child("usuarios")
            .child(usuarioId)
            .child("emails")
            .child(it.id!!)
            .setValue(it)
    }
}
