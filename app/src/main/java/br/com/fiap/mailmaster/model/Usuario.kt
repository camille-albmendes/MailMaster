package br.com.fiap.mailmaster.model

class Usuario : RemetenteInterface {
    var id: Long
    override var nome: String
    override var email: String

    constructor(id: Long, nome: String, email: String) {
        this.id = id
        this.nome = nome
        this.email = email
    }
}