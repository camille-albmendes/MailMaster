package br.com.fiap.mailmaster.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import br.com.fiap.mailmaster.LoginActivity
import br.com.fiap.mailmaster.MainActivity
import br.com.fiap.mailmaster.R
import br.com.fiap.mailmaster.ReadActivity
import br.com.fiap.mailmaster.filter.FiltroEmail
import br.com.fiap.mailmaster.model.Email
import br.com.fiap.mailmaster.repository.atualizarEmail
import br.com.fiap.mailmaster.repository.buscarEmails
import br.com.fiap.mailmaster.repository.filtrarEmails
import br.com.fiap.mailmaster.security.FirebaseUtils

@Composable
fun HomeScreen() {
    var emailsFetched by remember { mutableStateOf(false) }
    var emails = remember { mutableStateListOf<Email>()}

    if(!emailsFetched) {
        buscarEmails()
            .addOnSuccessListener {
                emails.clear()
                it.children.forEach() { email ->  emails.add(email.getValue(Email::class.java)!!) }
                emailsFetched = true
            }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(15.dp)
    ) {
        TopBar()
//        SearchBar()
        FilterBar(emails)
        MailList(emails, emailsFetched)
    }
}

@Composable
fun TopBar() {
    val ctx = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF8B0000))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.logomarca),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            FirebaseUtils.firebaseAuth.signOut()
                            ctx.startActivity(Intent(ctx, LoginActivity::class.java))
                        }
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search") },
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )
        Button(
            onClick = { /* Handle new mail action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000)),
            modifier = Modifier
                .padding(start = 8.dp)
                .height(40.dp)
                .defaultMinSize(minWidth = 120.dp)
        ) {
            Text("New Mail", color = Color.White)
        }
    }
}

@Composable
fun FilterBar(emails: SnapshotStateList<Email>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterButton("Tudo", Icons.Default.Email, emails, FiltroEmail())
        FilterButton("Ver depois", Icons.Default.Star, emails, FiltroEmail(false, true))
        FilterButton("Favoritos", Icons.Default.Favorite, emails, FiltroEmail(true, false))
    }
}

@Composable
fun FilterButton(text: String, icon: ImageVector, emails: SnapshotStateList<Email>, filtroEmail: FiltroEmail) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            buscarEmails()
                .addOnSuccessListener {
                    emails.clear()
                    filtrarEmails(
                        it.children.map() { email ->  email.getValue(Email::class.java)!! },
                        filtroEmail
                    )
                        .forEach{ email ->  emails.add(email) }
                }
        }
    ) {
        Icon(icon, contentDescription = text, modifier = Modifier.padding(end = 4.dp), tint = Color.Black)
        Text(text, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 12.sp)
    }
}

@Composable
fun MailList(emails: SnapshotStateList<Email>, emailsFetched: Boolean) {
    if(!emailsFetched) {
        Text(text = "Carregando...")
    } else if(emails.size > 0) {
        LazyColumn {
            items(emails.size) { index ->
                MailItem(emails[index])
            }
        }
    } else {
        Text(text = "Não há e-mails por aqui...")
    }
}

@Composable
fun MailItem(email: Email) {
    val sender = email.remetente!!.nome
    val time = email.data.toString()
    val message = email.assunto!!
    val isFavorite = email.favorito == true
    val isSaved = email.verDepois == true

    var favorite by remember { mutableStateOf(isFavorite) }
    var saved by remember { mutableStateOf(isSaved) }

    val usuarioId = FirebaseUtils.firebaseAuth.currentUser!!.uid
    val ctx = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
            .clickable {
                ReadActivity.email = email
                ctx.startActivity(Intent(ctx, ReadActivity::class.java))
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = sender,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(2f)
                ) {
                    Column {
                        //   Text(
                        //   text = title,
                        //    fontWeight = FontWeight.Bold,
                        //   modifier = Modifier.padding(bottom = 4.dp)
                        //   )
                        Text(
                            text = message,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.5f)
                ) {
                    IconButton(
                        onClick = {
                            favorite = !favorite
                            email.favorito = favorite
                            atualizarEmail(usuarioId, email)
                        },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = if (favorite) Color(0xFF8B0000) else Color.Gray)
                    ) {
                        Icon(
                            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(
                        onClick = {
                            saved = !saved
                            email.verDepois = saved;
                            atualizarEmail(usuarioId, email)
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = if (saved) Color(0xFF8B0000) else Color.Gray
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = if (saved) R.drawable.iconhiglighterfill else R.drawable.iconhiglighter),
                            contentDescription = "Save",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
