package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.viewModel.EquipoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("unused")
@Composable
fun EquiposScreen(
    navController: NavController,
    viewModel: EquipoViewModel
) {
    val equipos = viewModel.equipos.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Equipos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("agregarEquipo")
            }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (equipos.isEmpty()) {
                Text("No hay equipos registrados")
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(equipos) { equipo ->
                        EquipoCard(
                            equipo = equipo,
                            onDeleteClick = {
                                navController.navigate("eliminarEquipo/${equipo.idEquipo}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EquipoCard(
    equipo: Equipo,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Serial: ${equipo.serial}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Placa: ${equipo.placa}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Marca: ${equipo.marca?.nombre ?: "Sin marca"}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Tipo: ${equipo.tipoEquipo.nombre}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Estado: ${equipo.estado?.nombre ?: "Sin estado"}", style = MaterialTheme.typography.bodyMedium)
            }
            Button(
                onClick = { onDeleteClick() },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar")
            }
        }
    }
}

