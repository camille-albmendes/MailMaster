package br.com.fiap.mailmaster.model

import java.util.Date

class Email {
    var id: String?
    var assunto: String
    var conteudo: String
    var categorias: List<CategoriaEmail>
    var favorito: Boolean
    var verDepois: Boolean
    var data: Date

    constructor(
        id: String?,
        assunto: String,
        conteudo: String,
        categorias: List<CategoriaEmail>,
        favorito: Boolean,
        verDepois: Boolean,
        data: Date
    ) {
        this.id = id
        this.assunto = assunto
        this.conteudo = conteudo
        this.categorias = categorias
        this.favorito = favorito
        this.verDepois = verDepois
        this.data = data
    }

    constructor(
        id: String?,
        assunto: String,
        conteudo: String,
    ) {
        this.id = id
        this.assunto = assunto
        this.conteudo = conteudo
        this.categorias = ArrayList()
        this.favorito = false
        this.verDepois = false
        this.data = Date()
    }
}