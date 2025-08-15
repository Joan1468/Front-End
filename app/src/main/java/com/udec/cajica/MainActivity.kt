package com.udec.cajica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.udec.cajica.navigation.AppNavigator
import com.udec.cajica.ui.theme.CajicaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class    MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Habilita contenido edge-to-edge (detrás de status y navegación)
        enableEdgeToEdge()  // :contentReference[oaicite:6]{index=6}

        // 2. Define el contenido de la UI con Compose
        setContent {
            // 3. Aplica tu tema basado en Material 3
            CajicaTheme {
                // 4. Surface que pinta el fondo con colorScheme.background
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 5. Composable raíz que contiene el NavHost
                    AppNavigator()  // :contentReference[oaicite:7]{index=7}
                }
            }
        }
    }
}
