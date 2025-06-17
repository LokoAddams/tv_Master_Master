package com.example.tvmaster.Conexion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tvmaster.R


@Composable
fun ConexionUI(
    viewModel: ConexionViewModel = viewModel(),
    onBackClick: () -> Unit,
    onConexionExitosa: () -> Unit,
    onConexionFallida: () -> Unit)
{
    val estadoConexion by viewModel.conexionExitosa.collectAsState()

    // Ejecutar verificación al cargar la pantalla
    LaunchedEffect(Unit) {
        viewModel.verificarConexion()
    }

    // Navegar según el resultado
    LaunchedEffect(estadoConexion) {
        estadoConexion?.let { exitosa ->
            if (exitosa) {
                onConexionExitosa()
            } else {
                onConexionFallida()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Fondo negro
        contentAlignment = Alignment.Center)
    {
        Row (
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp))
        {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier.padding(1.dp))
            {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White)
            }
            Text(
                text = stringResource( id = R.string.conection),
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Spacer(modifier = Modifier.height(30.dp))
            // Icono de TV
            Image(
                painter = painterResource(id = R.drawable.tv),
                contentDescription = "TV",
                modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.puntitos_pantalla_conexion),
                contentDescription = "Puntitos de conexión",
                modifier = Modifier.size(50.dp))
            Image(
                painter = painterResource(id = R.drawable.puntitos_pantalla_conexion),
                contentDescription = "Puntitos de conexión",
                modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.wifi_si),
                contentDescription = "WiFi",
                modifier = Modifier.size(50.dp))
            Image(
                painter = painterResource(id = R.drawable.puntitos_pantalla_conexion),
                contentDescription = "Puntitos de conexión",
                modifier = Modifier.size(30.dp))
            Image(
                painter = painterResource(id = R.drawable.puntitos_pantalla_conexion),
                contentDescription = "Puntitos de conexión",
                modifier = Modifier.size(20.dp))
            Image(
                painter = painterResource(id = R.drawable.puntitos_pantalla_conexion),
                contentDescription = "Puntitos de conexión",
                modifier = Modifier.size(10.dp))
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.movil),
                contentDescription = "Móvil",
                modifier = Modifier.size(80.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ConexionScreenPreview() {
//    ConexionUI()
//}