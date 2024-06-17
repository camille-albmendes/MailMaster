package br.com.fiap.mailmaster.model

class CategoriaEmail {
    var id: Long
    var nome: String
    var cor: String
    var usuario: Usuario? = null

    constructor(id: Long, nome: String, cor: String, usuario: Usuario) {
        this.id = id
        this.nome = nome
        this.cor = cor
        this.usuario = usuario
    }

    constructor(id: Long, nome: String, cor: String) {
        this.id = id
        this.nome = nome
        this.cor = cor
    }
}