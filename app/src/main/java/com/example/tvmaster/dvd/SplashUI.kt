package com.example.tvmaster.dvd

import android.graphics.drawable.AnimatedVectorDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvmaster.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SignalAnimation(
    modifier: Modifier = Modifier,
    @DrawableRes drawableResId: Int = R.drawable.logo_animado
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                setImageResource(drawableResId)
                (drawable as? AnimatedVectorDrawable)?.start()
            }
        }
    )
}


@Composable
fun SplashUI(onSuccess : () -> Unit) {
//    var textColor by remember { mutableStateOf(Color.White) }
//    val transition = rememberInfiniteTransition()

//    val dx by transition.animateFloat(
//        initialValue = -100f,
//        targetValue = 100f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(2000, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//
//    val dy by transition.animateFloat(
//        initialValue = -100f,
//        targetValue = 100f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(2500, easing = LinearEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )

    // Cambio de color y navegación automática después de 3 segundos
    LaunchedEffect(Unit) {
//        repeat(1) {
//            delay(3000)
//            navController.navigate("menu") {
//                popUpTo("splash") { inclusive = true }
//            }
//        }
        delay(4000)
        onSuccess()
    }

//    // Cambio continuo de color del texto
//    LaunchedEffect(Unit) {
//        while (true) {
//            textColor = listOf(
//                Color.Red, Color.Green, Color.Blue,
//                Color.Yellow, Color.Cyan, Color.Magenta, Color.White
//            ).random()
//            delay(2000)
//        }
//    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "TV Master",
//            fontSize = 32.sp,
//            fontWeight = FontWeight.Bold,
//            color = textColor,
//            modifier = Modifier.offset(dx.dp, dy.dp)
//        )
//    }

    val celeste = Color(red = 12, green = 144, blue = 217)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignalAnimation(modifier = Modifier.size(200.dp))

//        Spacer(
//            modifier = Modifier
//                .height(1.dp)
//        )

        Text(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            text = "TV Master",
            color = celeste,
            fontSize = 25.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge.copy()
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview() {
//    // Creamos un NavController para el preview
//    val navController = rememberNavController()
//
//    // Definimos el NavHost con la ruta "splash"
//    NavHost(navController = navController, startDestination = "splash") {
//        composable("splash") {
//            SplashUI(navController)
//        }
//    }
//}
