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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarUsuarioScreen(navController: NavHostController, viewModel: UsuarioViewModel) {
    var idUsuario by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Usuario por ID") },
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
                label = { Text("ID Usuario") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    resultado = null
                    error = null

                    scope.launch {
                        try {
                            val usuario = viewModel.buscarUsuarioPorId(idUsuario.toLong())
                            resultado = "Nombre: ${usuario.nombre}\nCorreo: ${usuario.correo}"
                        } catch (e: Exception) {
                            error = "No se encontró el usuario o error: ${e.message}"
                        }
                    }
                },
                enabled = idUsuario.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar")
            }

            resultado?.let {
                Text(it, style = MaterialTheme.typography.bodyMedium)
            }

            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
