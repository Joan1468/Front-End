package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.viewModel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliminarUsuarioScreen(navController: NavHostController, viewModel: UsuarioViewModel) {
    var idUsuario by remember { mutableStateOf("") }
    val error = viewModel.error

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eliminar Usuario") },
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
            OutlinedTextField(
                value = idUsuario,
                onValueChange = { idUsuario = it },
                label = { Text("ID del usuario") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (idUsuario.isNotBlank()) {
                        viewModel.eliminarUsuario(idUsuario.toLong()) // ✅ CORREGIDO
                        navController.popBackStack()
                    }
                },
                enabled = idUsuario.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar")
            }

            if (error != null) {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
