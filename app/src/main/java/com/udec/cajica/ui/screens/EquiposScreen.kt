package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState // ✅ Import correcto
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.navigation.Screen
import com.udec.cajica.ui.components.EmptyState
import com.udec.cajica.ui.components.ErrorState
import com.udec.cajica.ui.components.LoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquiposScreen(
    navController: NavHostController,
    viewModel: com.udec.cajica.viewModel.EquipoViewModel
) {
    // ✅ Usar collectAsState() en lugar de .value
    val loading = viewModel.loading.collectAsState().value
    val error = viewModel.error.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Equipos",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.CrearEquipo.route) },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar equipo", tint = MaterialTheme.colorScheme.onPrimary)
                }

                FloatingActionButton(
                    onClick = { navController.navigate(Screen.BuscarEquipo.route) },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar equipo", tint = MaterialTheme.colorScheme.onPrimary)
                }

                FloatingActionButton(
                    onClick = { navController.navigate(Screen.EliminarEquipo.route) },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar equipo", tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when {
                loading -> LoadingState("Cargando equipos...")
                error != null -> ErrorState("Ocurrió un error: $error")
                else -> {
                    // ✅ Por ahora muestra vacío, sin equipos
                    EmptyState("No hay equipos registrados.")
                }
            }
        }
    }
}
