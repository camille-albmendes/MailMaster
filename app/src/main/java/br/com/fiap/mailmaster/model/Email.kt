package br.com.fiap.mailmaster.model

import java.util.Date

class Email {
    var id: String? = null
    var assunto: String? = null
    var conteudo: String? = null
    var remetente: RemetenteGenerico? = null
    var categorias: List<CategoriaEmail>? = null
    var favorito: Boolean? = null
    var verDepois: Boolean? = null
    var data: Date? = null
    var spam: Boolean? = null

    constructor() {}

    constructor(
        id: String?,
        assunto: String,
        conteudo: String,
        remetente: RemetenteGenerico,
        categorias: List<CategoriaEmail>,
        favorito: Boolean,
        verDepois: Boolean,
        data: Date,
        spam: Boolean
    ) {
        this.id = id
        this.assunto = assunto
        this.conteudo = conteudo
        this.remetente = remetente
        this.categorias = categorias
        this.favorito = favorito
        this.verDepois = verDepois
        this.data = data
        this.spam = spam
    }

    constructor(
        id: String?,
        assunto: String,
        conteudo: String,
        remetente: RemetenteGenerico
    ) {
        this.id = id
        this.assunto = assunto
        this.conteudo = conteudo
        this.remetente = remetente
        this.categorias = ArrayList()
        this.favorito = false
        this.verDepois = false
        this.data = Date()
        this.spam = false
    }
}
