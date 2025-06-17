package com.example.tvmaster.Settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
fun SettingsUI(isDarkTheme: Boolean, onToggleTheme: (Boolean) -> Unit, onBackClick: () -> Unit) {
    var themeExpanded by rememberSaveable { mutableStateOf(false) }
//    val isDarkTheme by viewModel.isDarkTheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), // Fondo negro
        contentAlignment = Alignment.TopCenter // Alineamos al centro superior
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Fondo oscuro
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
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = "Ajustes",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier
                .height(15.dp)
            )

            Column {
                Text(
                    text = "Theme",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { themeExpanded = !themeExpanded },
                    fontWeight = FontWeight.Bold
                )
                Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 2.dp)
            }

            AnimatedVisibility(visible = themeExpanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        onToggleTheme(false)
                        themeExpanded = false
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sol),
                            contentDescription = "Tema Claro",
                            tint = if (!isDarkTheme) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }

                    IconButton(onClick = {
                        onToggleTheme(true)
                        themeExpanded = false
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_luna),
                            contentDescription = "Tema Oscuro",
                            tint = if (isDarkTheme) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }

            SettingsOption(stringResource(id = R.string.customize_buttons))
            SettingsOption(stringResource(id = R.string.language_option))
            SettingsOption(stringResource(id = R.string.info_option))

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "${stringResource(id = R.string.app_version)}\n" +
                        "${stringResource(id = R.string.developers)}\n" +
                        "${stringResource(id = R.string.created_with)}\n" +
                        "${stringResource(id = R.string.leave_feedback)}",
                color = MaterialTheme.colorScheme.onBackground,
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
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsScreen() {
//    SettingsUI()
//}