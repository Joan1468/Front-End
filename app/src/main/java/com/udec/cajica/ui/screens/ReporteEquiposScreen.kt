package com.example.appinventario.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appinventario.viewmodel.EquiposViewModel
import com.udec.cajica.viewModel.EquipoViewModel

@Composable
fun ReporteEquiposScreen(navController: NavController, viewModel: EquipoViewModel) {
    val equipos by viewModel.listaEquipos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Reporte de Equipos") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Generar reporte en:", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                viewModel.exportarReportePDF(equipos)
            }) {
                Text("Exportar a PDF")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                viewModel.exportarReporteExcel(equipos)
            }) {
                Text("Exportar a Excel")
            }
        }
    }
}
