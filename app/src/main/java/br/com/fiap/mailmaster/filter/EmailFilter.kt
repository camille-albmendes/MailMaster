package br.com.fiap.mailmaster.filter

import br.com.fiap.mailmaster.model.CategoriaEmail
import br.com.fiap.mailmaster.model.Email

fun isSpam(email: Email): Boolean {
    val spamKeywords = listOf("viagra", "free", "money", "win", "prize")
    val spamSenders = listOf("spam@example.com", "unwanted@example.com")

    val containsSpamKeyword = spamKeywords.any { keyword ->
        email.assunto?.contains(keyword, ignoreCase = true) == true ||
                email.conteudo?.contains(keyword, ignoreCase = true) == true
    }

    val isSpamSender = email.remetente?.email in spamSenders

    return containsSpamKeyword || isSpamSender
}

class FiltroEmail {
    var id: String? = null
    var categorias: List<CategoriaEmail>? = null
    var favorito: Boolean? = null
    var verDepois: Boolean? = null
    var busca: String? = null
    var spam: Boolean? = null

    constructor() {}

    constructor(id: String?) {
        this.id = id
    }

    constructor(categorias: List<CategoriaEmail>) {
        this.categorias = categorias
    }

    constructor(favorito: Boolean, verDepois: Boolean) {
        this.favorito = favorito
        this.verDepois = verDepois
    }

    constructor(id: String?, busca: String?) {
        this.id = id
        this.busca = busca
    }

    constructor(busca: String, categorias: List<CategoriaEmail>, favorito: Boolean, verDepois: Boolean) {
        this.busca = busca
        this.categorias = categorias
        this.favorito = favorito
        this.verDepois = verDepois
    }

    fun matchEmail(email: Email): Boolean {
        var match = true

        if (
            (this.id != null && this.id != email.id) ||
            (this.favorito == true && !email.favorito!!) ||
            (this.verDepois == true && !email.verDepois!!) ||
            (!this.categorias.isNullOrEmpty() && !this.categorias!!.all { categoriaEmail -> !email.categorias.isNullOrEmpty() && email.categorias!!.contains(categoriaEmail) }) ||
            (!this.busca.isNullOrEmpty() && !email.assunto.isNullOrEmpty() && !email.assunto!!.lowercase().contains(this.busca!!.lowercase())) ||
            (this.spam == true && !isSpam(email))
        ) {
            match = false
        }

        return match
    }

    fun limparTudoMenosBusca() {
        this.id = null
        this.categorias = null
        this.favorito = null
        this.verDepois = null
        this.spam = null
    }

    fun limparTudo() {
        limparTudoMenosBusca()
        this.busca = null
    }
}
