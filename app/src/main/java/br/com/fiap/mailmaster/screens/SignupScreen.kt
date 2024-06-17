package br.com.fiap.mailmaster.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mailmaster.MainActivity
import br.com.fiap.mailmaster.R
import br.com.fiap.mailmaster.security.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen() {
    var email by remember () { mutableStateOf("") }
    var password by remember () { mutableStateOf("") }
    var name by remember () { mutableStateOf("") }
    var errorName by remember () { mutableStateOf("") }
    var dateofbirth by remember () { mutableStateOf("") }
    var cemail by remember () { mutableStateOf("") }
    var cpassword by remember () { mutableStateOf("") }

    val ctx = LocalContext.current

    Column (modifier = Modifier
        .background(color = colorResource(id =R.color.m_red)))

    { Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .fillMaxSize())

    { Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize())

    { Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxSize()
    )

    {
        Row {
            Image(painter = painterResource(id = R.drawable.logomarca), contentDescription = "", modifier = Modifier.size(80.dp))
            Text(text = stringResource(id = R.string.sign_up), fontSize = 18.sp,
                modifier = Modifier
                .padding(top = 30.dp))
        }

        Column {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    run {
                        name = it
                        errorName = validarCampo("nome", it)
                    }
                },
                supportingText = {
                    if (errorName != "") {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorName,
                            color = colorResource(id = android.R.color.white)
                        )
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(id = R.string.name), color = Color.Black)}
            )

            OutlinedTextField(value = dateofbirth, onValueChange = {dateofbirth = it},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(id = R.string.date_of_birth), color = Color.Black)})

            OutlinedTextField(value = email, onValueChange = {email = it},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(id = R.string.email), color = Color.Black)})

            OutlinedTextField(value = cemail, onValueChange = {cemail = it},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(id = R.string.cemail), color = Color.Black)})

            OutlinedTextField(value = password, onValueChange = {password = it},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(id = R.string.password), color = Color.Black)})

            OutlinedTextField(value = cpassword, onValueChange = {cpassword = it},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(id = R.string.cpassword), color = Color.Black)})

        }

        Row( modifier = Modifier
            .padding(top = 20.dp))
        {

            Button(onClick = { cadastrarUsuario(ctx, email, password) },
                modifier = Modifier
                    .padding(start = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black)))
            {
                Text(text = stringResource(id = R.string.sign_up), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.White)
            }
        }
    }
    }
    }
    }
}

fun validarCampo(campo: String, valor: String): String {
    // TODO completar validações
    if(campo == "nome") {
        return if(valor == "") "Este campo é obrigatório" else ""
    }

    return ""
}

fun cadastrarUsuario(ctx: Context, email: String, senha: String) {
    val firebaseAuth = FirebaseAuth.getInstance()
    firebaseAuth.createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Conta criada com sucesso!")
                sendEmailVerificacao()
                ctx.startActivity(Intent(ctx, MainActivity::class.java))
            } else {
                println("failed to Authenticate !")
            }
        }
}

fun sendEmailVerificacao() {
    FirebaseUtils.firebaseUser?.let {
        it.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // TODO mostrar para o usuário
                println("E-mail de confirmação enviado")
            }
        }
    }
}
