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
import com.udec.cajica.viewModel.EquipoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarEquipoScreen(
    navController: NavHostController,
    viewModel: EquipoViewModel
) {
    var idEquipo by remember { mutableStateOf("") }
    val error = viewModel.error.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Equipo") },
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
                value = idEquipo,
                onValueChange = { idEquipo = it },
                label = { Text("ID del equipo") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // Aquí llamas tu lógica para buscar el equipo en tu ViewModel
                    // Por ejemplo:
                    // viewModel.buscarEquipoPorId(idEquipo.toLong())
                },
                enabled = idEquipo.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar")
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
