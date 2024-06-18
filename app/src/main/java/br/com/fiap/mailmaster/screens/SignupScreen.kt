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

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mailmaster.MainActivity
import br.com.fiap.mailmaster.R
import java.text.SimpleDateFormat
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var errorName by remember { mutableStateOf("") }
    var dateofbirth by remember { mutableStateOf("") }
    var errorDateOfBirth by remember { mutableStateOf("") }
    var cemail by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf("") }
    var errorConfirmEmail by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf("") }
    var errorConfirmPassword by remember { mutableStateOf("") }
    var cpassword by remember { mutableStateOf("") }

    val ctx = LocalContext.current

    Column(modifier = Modifier.background(color = colorResource(id = R.color.m_red))) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxSize()
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.logomarca),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.sign_up),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 30.dp)
                        )
                    }

                    Column {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                // Filtrar apenas letras e espaços
                                if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                                    name = it
                                }
                                errorName = validarNome(it)
                            },
                            supportingText = {
                                if (errorName.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = errorName,
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .padding(horizontal = 40.dp),

                            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            label = { Text(text = stringResource(id = R.string.name), color = Color.Black) }
                        )

                        OutlinedTextField(
                            value = dateofbirth,
                            onValueChange = {
                                dateofbirth = formatarData(it)
                                errorDateOfBirth = validarData(dateofbirth)
                            },
                            supportingText = {
                                if (errorDateOfBirth.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = errorDateOfBirth,
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(horizontal = 40.dp),

                            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            label = { Text(text = stringResource(id = R.string.date_of_birth), color = Color.Black) }
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                errorEmail = validarEmail(it)
                            },
                            supportingText = {
                                if (errorEmail.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = errorEmail,
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(horizontal = 40.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            label = { Text(text = stringResource(id = R.string.email), color = Color.Black) }
                        )

                        OutlinedTextField(
                            value = cemail,
                            onValueChange = {
                                cemail = it
                                errorConfirmEmail = validarConfirmacaoEmail(email, it)
                            },
                            supportingText = {
                                if (errorConfirmEmail.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = errorConfirmEmail,
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(horizontal = 40.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            label = { Text(text = stringResource(id = R.string.cemail), color = Color.Black) }
                        )

                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                errorPassword = validarSenha(it)
                            },
                            supportingText = {
                                if (errorPassword.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = errorPassword,
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(horizontal = 40.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            label = { Text(text = stringResource(id = R.string.password), color = Color.Black) }
                        )

                        OutlinedTextField(
                            value = cpassword,
                            onValueChange = {
                                cpassword = it
                                errorConfirmPassword = validarConfirmacaoSenha(password, it)
                            },
                            supportingText = {
                                if (errorConfirmPassword.isNotEmpty()) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = errorConfirmPassword,
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(horizontal = 40.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                            shape = RoundedCornerShape(10.dp),
                            label = { Text(text = stringResource(id = R.string.cpassword), color = Color.Black) }
                        )
                    }

                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        Button(
                            onClick = {
                                val errors = validarFormulario(
                                    name, dateofbirth, email, cemail, password, cpassword
                                )
                                if (errors.isEmpty()) {
                                    cadastrarUsuario(ctx, name, email, password)
                                } else {
                                    // Atualizar estados de erro de acordo
                                    errorName = errors["Nome"] ?: ""
                                    errorDateOfBirth = errors["Data de Nascimento"] ?: ""
                                    errorEmail = errors["Email"] ?: ""
                                    errorConfirmEmail = errors["Confirmação de Email"] ?: ""
                                    errorPassword = errors["Senha"] ?: ""
                                    errorConfirmPassword = errors["Confirmação de Senha"] ?: ""
                                }
                            },
                            modifier = Modifier.padding(start = 10.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black))
                        ) {
                            Text(
                                text = stringResource(id = R.string.sign_up),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

fun validarNome(nome: String): String {
    return if (nome.trim().isEmpty()) {
        "O campo Nome é obrigatório."
    } else if (!Pattern.compile("^[a-zA-Z\\s]+$").matcher(nome).matches()) {
        "O nome deve conter apenas letras."
    } else {
        ""
    }
}

fun validarEmail(email: String): String {
    return if (email.trim().isEmpty()) {
        "O campo Email é obrigatório."
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        "Email inválido."
    } else {
        ""
    }
}

fun validarConfirmacaoEmail(email: String, confirmEmail: String): String {
    return if (confirmEmail.trim().isEmpty()) {
        "O campo de confirmação de email é obrigatório."
    } else if (email != confirmEmail) {
        "Os emails não coincidem."
    } else {
        ""
    }
}

fun validarSenha(senha: String): String {
    if (senha.trim().isEmpty()) {
        return "O campo Senha é obrigatório."
    }
    if (senha.length < 8) {
        return "A senha deve ter pelo menos 8 caracteres."
    }
    val pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).+\$")
    return if (!pattern.matcher(senha).matches()) {
        "A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais."
    } else {
        ""
    }
}

fun validarConfirmacaoSenha(senha: String, confirmSenha: String): String {
    return if (confirmSenha.trim().isEmpty()) {
        "O campo de confirmação de senha é obrigatório."
    } else if (senha != confirmSenha) {
        "As senhas não coincidem."
    } else {
        ""
    }
}

fun validarData(data: String): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    sdf.isLenient = false
    return try {
        sdf.parse(data)
        ""
    } catch (e: Exception) {
        "Formato de data inválido. Use dd/MM/yyyy."
    }
}

fun formatarData(input: String): String {
    var cleaned = input.replace(Regex("[^\\d]"), "")
    if (cleaned.length > 8) cleaned = cleaned.substring(0, 8)
    val builder = StringBuilder()
    for (i in cleaned.indices) {
        if (i == 2 || i == 4) {
            builder.append('/')
        }
        builder.append(cleaned[i])
    }
    return builder.toString()
}

fun validarFormulario(
    nome: String,
    dataNascimento: String,
    email: String,
    confirmEmail: String,
    senha: String,
    confirmSenha: String
): Map<String, String> {
    val erros = mutableMapOf<String, String>()

    erros["Nome"] = validarNome(nome)
    erros["Data de Nascimento"] = validarData(dataNascimento)
    erros["Email"] = validarEmail(email)
    erros["Confirmação de Email"] = validarConfirmacaoEmail(email, confirmEmail)
    erros["Senha"] = validarSenha(senha)
    erros["Confirmação de Senha"] = validarConfirmacaoSenha(senha, confirmSenha)

    return erros.filterValues { it.isNotEmpty() }
}

fun cadastrarUsuario(ctx: Context, nome: String, email: String, senha: String) {
    // Implementar o cadastro de usuário, como salvar no banco de dados ou enviar a um servidor
    val intent = Intent(ctx, MainActivity::class.java)
    ctx.startActivity(intent)
}