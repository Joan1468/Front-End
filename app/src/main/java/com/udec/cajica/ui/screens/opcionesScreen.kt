package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpcionesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Opciones") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate(Screen.CrearUsuario.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear usuario")
            }

            Button(
                onClick = { navController.navigate(Screen.BuscarUsuario.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar usuario")
            }

            Button(
                onClick = { navController.navigate(Screen.EliminarUsuario.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar usuario")
            }

            // Si quieres usar este botón, quita los comentarios
            /*
            Button(
                onClick = { navController.navigate(Screen.Equipo.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver equipos")
            }
            */
        }
    }
}
