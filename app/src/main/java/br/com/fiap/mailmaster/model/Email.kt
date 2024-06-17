package br.com.fiap.mailmaster.model

import java.util.Date

class Email {
    var id: Long
    var assunto: String
    var conteudo: String
    var remetente: RemetenteInterface
    var categorias: List<CategoriaEmail>
    var favorito: Boolean
    var verDepois: Boolean
    var data: Date

    constructor(
        id: Long,
        assunto: String,
        conteudo: String,
        remetente: RemetenteInterface,
        categorias: List<CategoriaEmail>,
        favorito: Boolean,
        verDepois: Boolean,
        data: Date
    ) {
        this.id = id
        this.assunto = assunto
        this.conteudo = conteudo
        this.remetente = remetente
        this.categorias = categorias
        this.favorito = favorito
        this.verDepois = verDepois
        this.data = data
    }

    constructor(
        id: Long,
        assunto: String,
        conteudo: String,
        remetente: RemetenteInterface,
    ) {
        this.id = id
        this.assunto = assunto
        this.conteudo = conteudo
        this.remetente = remetente
        this.categorias = ArrayList()
        this.favorito = false
        this.verDepois = false
        this.data = Date()
    }
}