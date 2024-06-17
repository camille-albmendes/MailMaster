package br.com.fiap.mailmaster.model

class RemetenteGenerico : RemetenteInterface {
    override var nome: String
    override var email: String

    constructor(nome: String, email: String) {
        this.nome = nome
        this.email = email
    }
}