package com.example.tvmaster.ConexionFailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvmaster.R

@Composable
fun ConexionFailedUI(
    OnClick : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Botón de Volver (arriba a la izquierda)
        Row (
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ){
            IconButton(
                onClick = { OnClick() },
                modifier = Modifier
                    .padding(1.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )
            }
            Text(
                text = stringResource(id = R.string.failed_conection),
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold

            )
        }


        // Botón de Ajustes (arriba a la derecha)
        IconButton(
            onClick = { /* Acción de ajustes */ },
            modifier = Modifier
                .align(Alignment.TopEnd) // Lo alinea en la esquina superior derecha
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Ajustes",
                tint = Color.White
            )
        }

        // Contenido centrado (No conectado, ícono y texto de error)
        Column(
            modifier = Modifier
                .align(Alignment.Center) // Centra todo en el medio de la pantalla
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.wifi_no),
                contentDescription = "Error de conexión",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.large_failed_conection),
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewConexionFallida() {
//    ConexionFailedUI()
//}
