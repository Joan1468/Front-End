package com.udec.cajica.ui.screens.equipos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Si AppNavigator es tu clase de navegación personalizada, asegúrate de importarla
import com.udec.cajica.navigation.AppNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleEquipoScreen(
    appNavigator: AppNavigator,
    equipoId: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Equipo") },
                navigationIcon = {
                    IconButton(onClick = { appNavigator.navigateBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "ID del Equipo: $equipoId",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Aquí van los detalles del equipo...")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Aquí podrías navegar a una pantalla de edición
                    appNavigator.navigateTo("editar_equipo/$equipoId")
                }
            ) {
                Text("Editar equipo")
            }
        }
    }
}
