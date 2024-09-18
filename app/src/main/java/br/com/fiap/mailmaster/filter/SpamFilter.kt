package br.com.fiap.mailmaster.filter

import br.com.fiap.mailmaster.model.Email
import java.net.InetAddress
import java.net.UnknownHostException

fun isPhishing(email: Email): Boolean {


    fun containsSuspiciousUrl(text: String?): Boolean {
        val urlPatterns = listOf(
            "http://", "https://", "www.", "bit.ly", "tinyurl.com", "goo.gl"
        )
        return text?.let {
            urlPatterns.any { pattern ->
                it.contains(pattern, ignoreCase = true)
            }
        } ?: false
    }


    fun isValidDomain(url: String?): Boolean {
        return try {
            val domain = url?.substringAfter("//")?.substringBefore("/") ?: return false
            InetAddress.getByName(domain)
            true
        } catch (e: UnknownHostException) {
            false
        }
    }


    fun containsSuspiciousUrlWithInvalidDomain(text: String?): Boolean {
        return text?.let {
            val urls = it.split(" ").filter { word -> word.startsWith("http://") || word.startsWith("https://") }
            urls.any { url ->
                containsSuspiciousUrl(url) && !isValidDomain(url)
            }
        } ?: false
    }

    val spamKeywords = listOf(
        "Phishing", "Fraude", "Lixo", "Golpe", "Malware", "Cancelar assinatura", "Em massa",
        "Suspeito", "Cliques enganadores", "Links de phishing", "Boato", "Publicidade", "Opt-out",
        "Junk", "Scam", "Unsubscribe", "Bulk", "Suspicious", "Clickbait", "Hoax", "Advertising"
    )


    val containsSuspiciousUrlInText = containsSuspiciousUrlWithInvalidDomain(email.assunto) ||
            containsSuspiciousUrlWithInvalidDomain(email.conteudo)


    val containsSpamKeyword = spamKeywords.any { keyword ->
        email.assunto?.contains(keyword, ignoreCase = true) == true ||
                email.conteudo?.contains(keyword, ignoreCase = true) == true
    }


    return containsSuspiciousUrlInText || containsSpamKeyword
}
