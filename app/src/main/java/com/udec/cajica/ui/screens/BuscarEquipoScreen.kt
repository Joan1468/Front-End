package com.udec.cajica.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.udec.cajica.viewModel.EquipoViewModel

@Composable
fun BuscarEquipoScreen(
    navController: NavHostController, // ✅ Agregado
    viewModel: EquipoViewModel
) {

@Composable
fun BuscarEquipoScreen(viewModel: EquipoViewModel = hiltViewModel()) {
    val query = remember { mutableStateOf(TextFieldValue("")) }
    val equipos by viewModel.equipos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = query.value,
            onValueChange = { query.value = it },
            label = { Text("Ingrese el ID del equipo") }
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (query.value.text.isNotEmpty()) {
                val id = query.value.text.toLongOrNull()
                if (id != null) {
                    viewModel.buscarEquipoPorId(id)
                }
            }
        }) {
            Text("Buscar")
        }

        Spacer(Modifier.height(16.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            error != null -> {
                Text("Error: $error", color = Color.Red)
            }
            else -> {
                if (equipos.isEmpty()) {
                    Text("No se encontraron equipos.")
                } else {
                    LazyColumn {
                        items(equipos) { equipo ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Column(Modifier.padding(8.dp)) {
                                    Text("ID: ${equipo.idEquipo}")

                                    Text("Serial: ${equipo.serial}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}}
