package com.udec.cajica.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.udec.cajica.data.model.Equipo

@Composable
fun ReporteEquiposScreen(
    equipos: List<Equipo>,
    onVerQR: (Equipo) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Reporte de Equipos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(equipos) { equipo ->
                ReporteEquipoItem(equipo = equipo, onVerQR = { onVerQR(equipo) })
            }
        }
    }
}

@Composable
fun ReporteEquipoItem(
    equipo: Equipo,
    onVerQR: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onVerQR() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Tipo: ${equipo.tipoEquipo.nombre}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Serial: ${equipo.serial}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Placa: ${equipo.placa}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Marca: ${equipo.marca?.nombre ?: "Sin marca"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Estado: ${equipo.estado?.nombre ?: "Sin estado"}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
