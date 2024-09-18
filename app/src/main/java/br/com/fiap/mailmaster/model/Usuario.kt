package br.com.fiap.mailmaster.model

class Usuario : RemetenteInterface {
    var id: String
    override var nome: String
    override var email: String

    constructor(id: String, nome: String, email: String) {
        this.id = id
        this.nome = nome
        this.email = email
    }
}