package br.com.fiap.mailmaster.repository

import br.com.fiap.mailmaster.model.Email
import com.google.firebase.database.FirebaseDatabase

val emailUm = Email(null, "Pagamento pendente - FIAP", "Pague. Apenas pague imediatamente")
val emailDois = Email(null, "Cupom gratuito", "Você ganhou um cupom. O código de uso é \"66-um-tapa-na-oreia\"")
val emailTres = Email(null, "Promoção samsung", "Nova promoção da samsung, pra você que cansou do travamento da motorola")

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
        "",
        ""
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
