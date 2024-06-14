package br.com.fiap.mailmaster.filter

import androidx.compose.ui.text.toLowerCase
import br.com.fiap.mailmaster.model.CategoriaEmail
import br.com.fiap.mailmaster.model.Email
import java.util.Locale

class FiltroEmail {
    var id: Long? = null
    var categorias: List<CategoriaEmail>? = null
    var favorito: Boolean? = null
    var verDepois: Boolean? = null
    var busca: String? = null

    constructor() {}

    constructor(id: Long?) {
        this.id = id
    }

    constructor(categorias: List<CategoriaEmail>) {
        this.categorias = categorias
    }

    constructor(favorito: Boolean, verDepois: Boolean) {
        this.favorito = favorito
        this.verDepois = verDepois
    }

    constructor(busca: String?) {
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

        if(
            (this.id != null && this.id != email.id) ||
            (this.favorito == true && !email.favorito) ||
            (this.verDepois == true && !email.verDepois) ||
            (!this.categorias.isNullOrEmpty() && !this.categorias!!.all { categoriaEmail -> email.categorias.contains(categoriaEmail) }) ||
            (!this.busca.isNullOrEmpty() && !email.assunto.lowercase().contains(this.busca!!.lowercase()))
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
    }

    fun limparTudo() {
        limparTudoMenosBusca()
        this.busca = null
    }
}