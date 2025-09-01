package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.viewModel.EquipoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EliminarEquipoScreen(navController: NavHostController, viewModel: EquipoViewModel) {
    var idEquipo by remember { mutableStateOf("") } // ✅ Estado local para el ID
    val error by viewModel.error.collectAsState() // ✅ Usar collectAsState para Flow
    val scope = rememberCoroutineScope() // ✅ Necesario para lanzar corrutina

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eliminar Equipo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
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
                value = idEquipo,
                onValueChange = { idEquipo = it },
                label = { Text("ID del equipo") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    scope.launch { // ✅ Corrutina para llamar a la función suspendida
                        if (idEquipo.isNotBlank()) {
                            viewModel.eliminarEquipo(idEquipo.toLong())
                            navController.popBackStack()
                        }
                    }
                },
                enabled = idEquipo.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar equipo")
            }

            // ✅ Mostrar error si existe
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
