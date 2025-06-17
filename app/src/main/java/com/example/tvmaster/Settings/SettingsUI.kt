package com.example.tvmaster.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvmaster.R


@Composable
fun SettingsUI(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Fondo negro
        contentAlignment = Alignment.TopCenter // Alineamos al centro superior
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)) // Fondo oscuro
                .padding(16.dp)
        ) {

            // 🔼 Encabezado con flecha y texto "Ajustes"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackClick() },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Ajustes",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Opciones de ajustes
            SettingsOption(stringResource(id = R.string.theme_option))
            SettingsOption(stringResource(id = R.string.customize_buttons))
            SettingsOption(stringResource(id = R.string.language_option))
            SettingsOption(stringResource(id = R.string.info_option))

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "${stringResource(id = R.string.app_version)}\n" +
                        "${stringResource(id = R.string.developers)}\n" +
                        "${stringResource(id = R.string.created_with)}\n" +
                        "${stringResource(id = R.string.leave_feedback)}",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SettingsOption(title: String) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Divider(color = Color.White, thickness = 1.dp)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsScreen() {
//    SettingsUI()
//}