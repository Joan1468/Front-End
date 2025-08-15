package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.data.model.Estado
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.data.model.Marca
import com.udec.cajica.data.model.TipoEquipo
import com.udec.cajica.viewModel.EquipoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearEquipoScreen(
    navController: NavHostController,
    viewModel: EquipoViewModel
) {
    var serial by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var tipoEquipoNombre by remember { mutableStateOf("") }
    var marcaNombre by remember { mutableStateOf("") }
    var estadoNombre by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Equipo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = serial,
                onValueChange = { serial = it },
                label = { Text("Serial") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = placa,
                onValueChange = { placa = it },
                label = { Text("Placa") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = tipoEquipoNombre,
                onValueChange = { tipoEquipoNombre = it },
                label = { Text("Tipo de equipo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = marcaNombre,
                onValueChange = { marcaNombre = it },
                label = { Text("Marca") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = estadoNombre,
                onValueChange = { estadoNombre = it },
                label = { Text("Estado") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val equipo = Equipo(
                        idEquipo = 0L,
                        serial = serial,
                        placa = placa,
                        tipoEquipo = TipoEquipo(idTipoEquipo = 0L, nombre = tipoEquipoNombre.ifBlank { "Otro" }),
                        marca = Marca(idMarca = 0L, nombre = marcaNombre.ifBlank { "Desconocida" }),
                        estado = Estado(idEstado = 0L, nombre = estadoNombre.ifBlank { "Bueno" })
                    )
                    viewModel.guardarEquipo(equipo)
                    navController.popBackStack()
                },
                enabled = serial.isNotBlank() && placa.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear equipo")
            }
        }
    }
}
