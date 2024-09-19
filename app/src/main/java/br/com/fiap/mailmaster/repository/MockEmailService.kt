package br.com.fiap.mailmaster.repository

import br.com.fiap.mailmaster.model.Email

class MockEmailService {

    private val emailList = mutableListOf<Email>()

    init {
        // Adicionando e-mails mock
        emailList.add(
            Email(
                id = "1",
                sender = "johndoe@gmail.com",
                recipient = "you@example.com",
                subject = "Bem-vindo ao MailMaster",
                body = "Obrigado por se cadastrar no MailMaster.",
                timestamp = System.currentTimeMillis()
            )
        )
        emailList.add(
            Email(
                id = "2",
                sender = "janedoe@gmail.com",
                recipient = "you@example.com",
                subject = "Evento no Calendário",
                body = "Você foi convidado para o evento 'Reunião Mensal'.",
                timestamp = System.currentTimeMillis()
            )
        )
    }



    // Listar todos os e-mails
    fun listEmails(): List<Email> {
        return emailList
    }

    // Obter os detalhes de um e-mail pelo ID
    fun getEmailDetails(emailId: String): Email? {
        return emailList.find { it.id == emailId }
    }

    // Enviar um e-mail (simulação)
    fun sendEmail(email: Email): Boolean {
        // Simular envio de e-mail, sempre retorna true
        emailList.add(email)
        return true
    }
}
