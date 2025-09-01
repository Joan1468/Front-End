package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.data.model.Estado
import com.udec.cajica.data.model.EquipoDto
import com.udec.cajica.data.model.Marca
import com.udec.cajica.data.model.TipoEquipo
import com.udec.cajica.viewModel.EquipoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearEquipoScreen(
    navController: NavHostController,
    viewModel: EquipoViewModel
) {
    // campos de texto (strings) que rellenará el usuario
    var idEquipoText by remember { mutableStateOf("") }          // id como String (input)
    var serial by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var tipoEquipoNombre by remember { mutableStateOf("") }      // nombre del tipo (entrada)
    var marcaNombre by remember { mutableStateOf("") }           // nombre de marca (entrada)
    var estadoNombre by remember { mutableStateOf("") }         // nombre estado (entrada)

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
                value = idEquipoText,
                onValueChange = { idEquipoText = it },
                label = { Text("ID equipo:") },
                modifier = Modifier.fillMaxWidth()
            )

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
                label = { Text("Tipo de equipo (nombre)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = marcaNombre,
                onValueChange = { marcaNombre = it },
                label = { Text("Marca (nombre)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = estadoNombre,
                onValueChange = { estadoNombre = it },
                label = { Text("Estado (nombre)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // --- conversiones / mapeos necesarios para evitar type mismatches ---
                    val idEquipoLong: Long = idEquipoText.toLongOrNull() ?: 0L

                    val tipoEquipoObj = TipoEquipo(
                        idTipoEquipo = 0L,
                        nombre = tipoEquipoNombre.ifBlank { "Otro" }
                    )
                    val marcaObj = Marca(
                        idMarca = 0L,
                        nombre = marcaNombre.ifBlank { "Desconocida" }
                    )
                    val estadoObj = Estado(
                        idEstado = 0L,
                        nombre = estadoNombre.ifBlank { "Bueno" }
                    )

                    val equipoDto = EquipoDto(
                        idEquipo = if (idEquipoLong == 0L) null else idEquipoLong,
                        serial = serial,
                        placa = placa,
                        idTipoEquipo = tipoEquipoObj.idTipoEquipo,
                        idMarca = marcaObj.idMarca,
                        idEstado = estadoObj.idEstado,
                        idInventario = 0L
                    )

                    // ✅ CAMBIO: La función que sí existe en tu ViewModel se llama `createEquipo`.
                    // Antes se llamaba a 'guardarEquipo' (no existente) y por eso daba error.
                    viewModel.createEquipo(equipoDto) // ✅ CAMBIO: uso createEquipo en vez de guardarEquipo

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
