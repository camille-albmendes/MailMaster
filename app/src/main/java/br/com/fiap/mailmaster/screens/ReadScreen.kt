package br.com.fiap.mailmaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar()
        Spacer(modifier = Modifier.height(8.dp))
        EmailDetail()
        ReplySection()
    }
}

@Composable
fun CustomTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF8B0000))
            .padding(8.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Handle return action */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Return",
                    tint = Color.White
                )
            }
            Text(
                text = "M",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
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

@Composable
fun EmailDetail() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "20:30",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Nubank",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { /* Handle favorite action */ },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Gray)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                    IconButton(
                        onClick = { /* Handle save action */ },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Gray)
                    ) {

                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Entenda suas Finanças",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = """
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sapien lorem, pretium quis ipsum sit amet, aliquet placerat dui. Fusce sagittis venenatis urna, eget lacinia dolor accumsan nec. Sed molestie mi velit, eget elementum est accumsan a. Cras dictum ante sed sapien imperdiet convallis. Nunc nulla risus, interdum in tempus vel, sodales ut nibh. In sit amet purus eget ipsum malesuada viverra vitae in felis. Sed hendrerit sollicitudin libero, quis auctor diam condimentum eget. In hac habitasse platea dictumst. Sed vel posuere mi, id cursus libero. Mauris nec leo nec lorem mattis maximus. Fusce ligula erat, bibendum tincidunt id, volutpat at libero. Nulla viverra sollicitudin nunc, ut ultricies nisl aliquet id. Nam porttitor ullamcorper risus a tempor. Cras a lobortis nisl. Morbi convallis, nunc luctus aliquam ultricies, lorem nunc volutpat magna, sit amet ultrices quam mauris eu libero.
                    """.trimIndent(),
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ReplySection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle reply action */ }) {

        }
        Button(
            onClick = { /* Handle reply action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000)),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Reply", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
