package br.com.fiap.mailmaster.repository

import br.com.fiap.mailmaster.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

fun cadastrarUsuario(nome: String, email: String, senha: String): Task<AuthResult> {
    val firebaseAuth = FirebaseAuth.getInstance()
    return firebaseAuth.createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
                val ref = firebaseDatabase.getReference()

                ref
                    .child("usuarios")
                    .child(firebaseAuth.currentUser!!.uid)
                    .setValue(Usuario(firebaseAuth.currentUser!!.uid, nome, email))
                    .addOnCompleteListener { taskUser ->
                        if (taskUser.isSuccessful) {
                            println("Conta criada com sucesso!")
                            criarEmailBoasVindas(firebaseAuth.currentUser!!.uid)
                            criarEmailsMock(firebaseAuth.currentUser!!.uid)
                        }
                    }
            } else {
                println("Falha ao autenticar!")
            }
        }
}

fun verificarEnviosRecentesUsuario(usuarioId: String, maxEnvios: Int): Task<Boolean> {
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val ref = firebaseDatabase.getReference()

    val dataLimite = System.currentTimeMillis() - 24 * 60 * 60 * 1000 // Últimas 24 horas
    return ref
        .child("usuarios")
        .child(usuarioId)
        .child("envios")
        .orderByChild("timestamp")
        .startAt(dataLimite.toDouble())
        .get()
        .continueWith { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                val enviosRecentes = snapshot?.children?.count() ?: 0
                enviosRecentes < maxEnvios
            } else {
                throw task.exception ?: Exception("Erro ao verificar envios recentes")
            }
        }
}
