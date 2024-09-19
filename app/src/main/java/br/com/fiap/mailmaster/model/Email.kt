package br.com.fiap.mailmaster.model

import java.util.Date

// Supondo que RemetenteGenerico e CategoriaEmail estejam definidos em outro lugar
data class Email(
    var id: String,                // ID do e-mail
    val sender: String,            // Remetente
    val recipient: String,         // Destinatário
    var subject: String?,          // Assunto (subject)
    var body: String?,             // Conteúdo (body)
    var remetente: RemetenteGenerico?, // Remetente genérico (se você quiser manter)
    var categorias: List<CategoriaEmail>?, // Categorias de e-mail
    var favorito: Boolean?,        // Se o e-mail é favoritado
    var verDepois: Boolean?,       // Se será lido depois
    var data: Date?,               // Data de envio
    var timestamp: Long            // Carimbo de data/hora
) {
    constructor(
        id: String,
        subject: String,
        body: String,
        sender: String,
        recipient: String,
        timestamp: Long
    ) : this(
        id,
        sender,                  // Usa o parâmetro sender diretamente
        recipient,               // Usa o parâmetro recipient diretamente
        subject,
        body,
        null,                    // Não usa remetente
        emptyList(),
        false,
        false,
        Date(),
        System.currentTimeMillis()
    )
}
