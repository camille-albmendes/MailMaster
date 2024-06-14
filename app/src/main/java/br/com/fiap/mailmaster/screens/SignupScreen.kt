package br.com.fiap.mailmaster.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.mailmaster.R

@Composable
fun SignupScreen() {
    Column (modifier = Modifier
        .background(color = Color.Black))

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

        modifier = Modifier
            .padding(top = 90.dp)
            .fillMaxSize()
    ){
        Text(
            text = stringResource(id = R.string.sign_up),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
    }
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
        ) {}
    }
    }
    }
}