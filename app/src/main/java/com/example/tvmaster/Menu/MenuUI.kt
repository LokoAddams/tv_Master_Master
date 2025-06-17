package com.example.tvmaster.Menu

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvmaster.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tvmaster.service.Util
@Composable
fun MenuUI(
    onControlClick: () -> Unit,
    onAjustesClick: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel()
)
{
    val context = LocalContext.current

    val uiState = viewModel.state.collectAsState()

    // Cargar televisores una sola vez
    LaunchedEffect(Unit) {
        viewModel.cargarTelevisores()
    }
    LaunchedEffect(uiState.value) {
        when (val state = uiState.value) {
            is MenuViewModel.TelevisoresState.Mostrar -> {
                Util.sendNotificatión(context, "Se recuperaron ${state.televisores.size} televisores.")
            }
            is MenuViewModel.TelevisoresState.NoHayTelevisores -> {
                Util.sendNotificatión(context, "No se encontraron televisores guardados.")
            }
            else -> {}
        }
    }
    val semiPlomo = Color(red = 40, green = 40, blue = 40, alpha = 180)
    val plomoClaro = Color(red = 61, green = 61, blue = 61)
    val plomo = Color(red = 40, green = 40, blue = 40)
    val semiNegro = Color(red = 0, green = 0, blue = 0, alpha = 180)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.fondomenu),
                contentScale = ContentScale.Crop
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
//                    Brush.verticalGradient(
//                        colors = listOf(
//                            Color(0xCC000000),
//                            Color.Transparent//Lo pone transparente
//                        ),
//                        startY = 0f,
//                        endY = Float.POSITIVE_INFINITY
//                    )
                    color = semiNegro
                )
        )
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    modifier = Modifier
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = { onControlClick() }
                ) {
//                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Control",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleSmall.copy()
                    )
                }

                Button(
                    modifier = Modifier
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = plomoClaro,
                        contentColor = Color.White
                    ),
                    onClick = { onAjustesClick() }
                ) {
                    Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Ajustes",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleSmall.copy()
                    )
                }
            }



            Column(
                modifier = Modifier
                    .width(320.dp)
                    .height(500.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        "Televisores",
                        fontSize = 21.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge.copy()
                    )
                }
                // 👇 Aquí mostramos la lista o el mensaje según el estado
                when (val state = uiState.value) {
                    is MenuViewModel.TelevisoresState.Mostrar -> {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp)
                        ) {
                            state.televisores.forEach { tv ->
                                Text(
                                    text = tv.friendlyName ?: "TV sin nombre",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .background(Color.DarkGray, RoundedCornerShape(8.dp))
                                        .padding(12.dp)
                                )
                            }
                        }
                    }

                    is MenuViewModel.TelevisoresState.NoHayTelevisores -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay televisores guardados.",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelLarge.copy()
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewMenuPantalla() {
//    MenuUI()
//}