package br.com.fiap.mailmaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        SearchBar()
        FilterBar()
        MailList()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF8B0000))
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp)), // Arredondando os cantos com 20dp de curva
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "M",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                tint = Color.White
            )
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
                containerColor = Color.White, // Cor de fundo branca
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier
                .weight(1f)
                .height(40.dp) // Altura da barra de busca
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )
        Button(
            onClick = { /* Handle new mail action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000)),
            modifier = Modifier
                .padding(start = 8.dp)
                .height(40.dp) // Altura do botão
                .defaultMinSize(minWidth = 120.dp) // Largura mínima do botão
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
        Icon(icon, contentDescription = text, modifier = Modifier.padding(end = 4.dp))
        Text(text, fontWeight = FontWeight.Bold)
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
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(1.dp, Color.Black, RectangleShape), // Borda preta
        colors = CardDefaults.cardColors(containerColor = Color.White), // Fundo branco
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier
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
                    modifier = Modifier.weight(2f) // Tamanho ajustável para a mensagem
                )
                IconButton(
                    onClick = { favorite = !favorite },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = if (favorite) Color(0xFF8B0000) else Color.Gray) // Cor do ícone
                ) {
                    Icon(
                        imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
                IconButton(
                    onClick = { saved = !saved },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = if (saved) Color(0xFF8B0000) else Color.Gray) // Cor do ícone
                ) {

                }
            }
            Text(
                text = time,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp) // Espaçamento entre a mensagem e o horário
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
