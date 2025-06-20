package com.example.tvmaster.control

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.DispTV
import com.example.tvmaster.Manager
import com.example.tvmaster.R
import com.example.tvmaster.service.Util

@Composable
fun ControlUI(
    onClick: () -> Unit,
    viewModel: ControlViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val activity = context as? Activity ?: return
    // Solo una vez: crear manager y pasarle el activity
    val connectManager = remember { Manager(activity) }


    var currentAppPage by remember { mutableStateOf(0) }
    val appPages = listOf(
        listOf("Netflix", "YouTube", "Google"),
        listOf( "HBO Max", "Disney+", "Amazon Prime"),
        listOf("Spotify", "Twitch", "Facebook")
    )

    var showMousepad by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(3.dp, Color.Gray, RoundedCornerShape(24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Botón de volver
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
            }
            Text("Volver", color = Color.White, fontSize = 18.sp)
        }

        // Apagar / Home
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ControlButton(icon = Icons.Filled.PowerSettingsNew, onClick = { Util.sendNotificatión(context, connectManager.tvOff()) }, buttonColor = Color.Red)
            val nombre = remember { mutableStateOf("Desconocido") }

            ControlButton(
                icon = Icons.Filled.AddToQueue,
                onClick = {
                    connectManager.hConnectToggle()

                },
                buttonColor = Color.Cyan
            )
            ControlButton(icon = Icons.Filled.Home, onClick = {Util.sendNotificatión(context, connectManager.home())}, buttonColor = Color.Blue)
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (showMousepad) {
            var lastOffset by remember { mutableStateOf<Offset?>(null) }

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.DarkGray)
                    .pointerInput(Unit) {
                        awaitEachGesture {
                            val down = awaitFirstDown()
                            lastOffset = down.position
                            var moved = false

                            drag(down.id) { change ->
                                val currentOffset = change.position
                                lastOffset?.let { last ->
                                    val dx = (currentOffset.x - last.x).toDouble() / 2
                                    val dy = (currentOffset.y - last.y).toDouble() / 2
                                    if (dx != 0.0 || dy != 0.0) {
                                        connectManager.moveMouse(dx, dy)
                                        moved = true
                                    }
                                }
                                lastOffset = currentOffset
                                change.consume()
                            }

                            if (!moved) {
                                connectManager.clickMouse()
                            }

                            lastOffset = null
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("Mousepad", color = Color.White)
            }
        }
        else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ControlButton(icon = Icons.Filled.KeyboardArrowUp, onClick = {
                    if("Error: Tv no conectada" == connectManager.up())
                        Util.sendNotificatión(context, "Error: Tv no conectada")
                }, buttonColor = Color.LightGray)
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    ControlButton(icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft, onClick = {
                        if("Error: Tv no conectada" == connectManager.left())
                            Util.sendNotificatión(context, "Error: Tv no conectada")
                    }, buttonColor = Color.LightGray)
                    Spacer(modifier = Modifier.width(20.dp))
                    ControlButtonWithText(onClick = {
                        if("Error: Tv no conectada" == connectManager.okay())
                            Util.sendNotificatión(context, "Error: Tv no conectada")
                    }, buttonColor = Color(rgb(81, 247, 89)) , text = "OK")
                    Spacer(modifier = Modifier.width(20.dp))
                    ControlButton(icon = Icons.AutoMirrored.Filled.KeyboardArrowRight, onClick = {
                        if("Error: Tv no conectada" == connectManager.right())
                            Util.sendNotificatión(context, "Error: Tv no conectada")
                    }, buttonColor = Color.LightGray)
                }
                Spacer(modifier = Modifier.height(20.dp))
                ControlButton(icon = Icons.Filled.KeyboardArrowDown, onClick = {
                    if("Error: Tv no conectada" == connectManager.down())
                    Util.sendNotificatión(context, "Error: Tv no conectada")
                                                                               }, buttonColor = Color.LightGray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones volumen y canal agrupados
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ControlButton(icon = Icons.AutoMirrored.Filled.VolumeUp, onClick = {
                    if("Error: Tv no conectada" == connectManager.volumenUp())
                        Util.sendNotificatión(context, "Error: Tv no conectada")
                }, buttonColor = Color.Gray)
                ControlButton(icon = Icons.AutoMirrored.Filled.VolumeDown, onClick = {
                    if("Error: Tv no conectada" == connectManager.volumenDown())
                        Util.sendNotificatión(context, "Error: Tv no conectada")
                }, buttonColor = Color.Gray)
            }

            // Botón Atrás centrado verticalmente usando un Box
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(70.dp), // Ajusta el ancho si es necesario
                contentAlignment = Alignment.Center
            ) {
                ControlButton(icon = Icons.AutoMirrored.Filled.ArrowBack, onClick = {
                    if("Error: Tv no conectada" == connectManager.back())
                        Util.sendNotificatión(context, "Error: Tv no conectada")
                }, buttonColor = Color.Gray)
            }

            Column(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ControlButton(icon = Icons.Default.Add, onClick = {
                    if("Error: Tv no conectada" == connectManager.channelUp())
                        Util.sendNotificatión(context, "Error: Tv no conectada")
                }, buttonColor = Color.Gray)
                ControlButton(icon = Icons.Default.Remove, onClick = {
                    if("Error: Tv no conectada" == connectManager.channelDown())
                        Util.sendNotificatión(context, "Error: Tv no conectada")
                }, buttonColor = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de apps con flechas para cambiar página
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                currentAppPage = (currentAppPage - 1 + appPages.size) % appPages.size
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Izquierda", tint = Color.White)
            }

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.weight(1f)) {
                appPages[currentAppPage].forEach { appName ->
                    IconButton(
                        onClick = {
                            if("Error: Tv no conectada" == connectManager.lauch(appName))
                                Util.sendNotificatión(context, "Error: Tv no conectada")
                                  },
                        modifier = Modifier.size(60.dp)
                    )   {
                        Icon(
                            painter = painterResource(
                                id = when (appName) {
                                    "Netflix" -> R.drawable.netflix
                                    "YouTube" -> R.drawable.youtube
                                    "Google" -> R.drawable.google
                                    "HBO Max" -> R.drawable.hbo_max
                                    "Disney+" -> R.drawable.disney
                                    "Amazon Prime" -> R.drawable.amazon_prime
                                    "Spotify" -> R.drawable.spotify
                                    "Twitch" -> R.drawable.twitch
                                    "Facebook" -> R.drawable.facebook

                                    else -> R.drawable.ic_launcher_foreground
                                }
                            ),
                            contentDescription = appName,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(300.dp)
                        )

                    }
                }
            }

            IconButton(onClick = {
                currentAppPage = (currentAppPage + 1) % appPages.size
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Derecha", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Switch Mouse / Flechas
        Button(onClick = { showMousepad = !showMousepad }) {
            Text(if (showMousepad) "Mostrar Flechas" else "Mostrar Mouse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val tvName = connectManager.mTV?.friendlyName

                    // Guardar solo si el nombre no es "Desconocido"
                    if (tvName != null) {
                        val dispositivo = DispTV(friendlyName = tvName)
                        viewModel.guardarTV(dispositivo)
                    }

        }) {
            Text("Guardar TV")
        }
    }
}

@Composable
fun ControlButton(icon: ImageVector, onClick: () -> Unit, buttonColor: Color) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(70.dp)
            .padding(4.dp)
            .background(buttonColor, shape = CircleShape)
            .shadow(4.dp, shape = CircleShape)
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.White)
    }
}
@Composable
fun ControlButtonWithText(onClick: () -> Unit, buttonColor: Color, text: String) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .padding(4.dp)
            .background(buttonColor, shape = CircleShape)
            .shadow(4.dp, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview(showBackground = true)
//@Composable
//fun PreviewControlScreen() {
//    ControlUI()
//}