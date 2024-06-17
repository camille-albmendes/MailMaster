package br.com.fiap.mailmaster.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mailmaster.R

@Composable
fun ReadScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(15.dp)
    ) {
       TopBar()
        Return()
        MessageField()
    }
}


@Composable
fun Return() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "",
            tint = Color.Black
        )
        Text(text = stringResource(id = R.string.returnsc), color = Color.Black)
    }

}

@Composable
fun MessageField() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "20:30",
                fontSize = 11.sp,
                color = Color.Gray
            )

            Text(
                text = "Nubank",
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Entenda suas Finanças",
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sapien lorem, pretium quis ipsum sit amet, aliquet placerat dui. Fusce sagittis venenatis urna, eget lacinia dolor accumsan nec. Sed molestie mi velit, eget elementum est accumsan at. Cras dictum ante sed sapien imperdiet convallis. Nunc nulla risus, interdum in tempus vel, sodales ut nibh. In sit amet purus eget ipsum malesuada viverra vitae in felis. Sed hendrerit sollicitudin libero, quis auctor diam condimentum eget. In hac habitasse platea dictumst. Sed vel posuere mi, id cursus libero. Mauris nec leo nec lorem mattis maximus. Fusce ligula erat, bibendum non euismod id, volutpat at libero. Nulla viverra sollicitudin nunc, eu ultricies nisi aliquet id. Nam porttitor ullamcorper risus a tempor. Cras a purus vel mauris ultricies gravida eu eu dui. Suspendisse non finibus dolor, ac consequat nibh. Morbi convallis, nunc luctus aliquam finibus, mauris nunc volutpat magna, sit amet ultrices quam mauris eu libero.",
                fontSize = 15.sp,
                color = Color.Black
            )
        }
    }


}

