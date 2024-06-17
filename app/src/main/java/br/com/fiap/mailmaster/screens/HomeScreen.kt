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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import br.com.fiap.mailmaster.LoginActivity
import br.com.fiap.mailmaster.R
import br.com.fiap.mailmaster.security.FirebaseUtils

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(15.dp)
    ) {
        TopBar()
        SearchBar()
        FilterBar()
        MailList()
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
fun FilterBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterButton("All", Icons.Default.Email)
        FilterButton("Markers", Icons.Default.Star)
        FilterButton("Favorites", Icons.Default.Favorite)
        FilterButton("Calendar", Icons.Default.DateRange)
    }
}

@Composable
fun FilterButton(text: String, icon: ImageVector) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = text, modifier = Modifier.padding(end = 4.dp), tint = Color.Black)
        Text(text, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 12.sp)
    }
}

@Composable
fun MailList() {
    LazyColumn {
        items(10) { index ->
            MailItem(
                sender = "Sender $index",
                time = "20:30",
                message = "Sample message $index",
                isFavorite = index % 2 == 0,
                isSaved = index % 3 == 0
            )
        }
    }
}

@Composable
fun MailItem(
    sender: String,
    time: String,
    message: String,
    isFavorite: Boolean,
    isSaved: Boolean
) {
    var favorite by remember { mutableStateOf(isFavorite) }
    var saved by remember { mutableStateOf(isSaved) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sender,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = message,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.weight(2f)
                )

                // Coluna para os ícones
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    IconButton(
                        onClick = { favorite = !favorite },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = if (favorite) Color(0xFF8B0000) else Color.Gray)
                    ) {
                        Icon(
                            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(
                        onClick = { saved = !saved },
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

            // Novo Row para o texto do tempo
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

