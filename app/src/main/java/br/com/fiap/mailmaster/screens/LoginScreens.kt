package br.com.fiap.mailmaster.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.navigation.NavHostController
import br.com.fiap.mailmaster.MainActivity
import br.com.fiap.mailmaster.R
import br.com.fiap.mailmaster.security.FirebaseUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    var email by remember () { mutableStateOf("") }
    var password by remember () { mutableStateOf("") }

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
            .padding(top = 100.dp)
            .fillMaxSize()
    )

    {
        Row {
            Image(painter = painterResource(id = R.drawable.logomarca), contentDescription = "", modifier = Modifier.size(150.dp))


        }
        Text(text = stringResource(id = R.string.slogan), fontSize = 12.sp)

        Column {
            OutlinedTextField(value = email, onValueChange = {email = it},
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {Icon(imageVector = Icons.Filled.Email, contentDescription = "")},
                label = { Text(text = stringResource(id = R.string.email), color = Color.Black)})

            OutlinedTextField(value = password, onValueChange = {password = it},
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 40.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                leadingIcon = {Icon(imageVector = Icons.Filled.Lock, contentDescription = "")},
                label = { Text(text = stringResource(id = R.string.password), color = Color.Black)})
        }

        Row( modifier = Modifier
            .padding(top = 20.dp))
        {
            Button(onClick = {
                login(ctx, email, password)
            },
                modifier = Modifier
                    .padding(start = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black)))
            {
                Text(text = stringResource(id = R.string.login), fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.White)
            }
        }

        Row (modifier = Modifier.padding(top = 50.dp)) {
            Text(text = stringResource(id = R.string.no_account), fontSize = 12.sp)
            Text(text = stringResource(id = R.string.sign_up),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable { navController.navigate("cadastro") }
            )
        }
    }
    }
    }
    }
}

fun login(ctx: Context, email: String, senha: String) {
    val signInEmail = email.trim()
    val signInPassword = senha.trim()

    // TODO validação
    if (true) {
        FirebaseUtils.firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
            .addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    ctx.startActivity(Intent(ctx, MainActivity::class.java))
                } else {
                    // TODO mostrar erro de login para o usuário
                }
            }
    } else {
        // TODO mostrar erros de validação
    }
}
