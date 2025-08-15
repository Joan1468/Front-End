package com.udec.cajica.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.udec.cajica.data.model.Equipo // Asegúrate de tener el modelo

@Composable
fun EquipoCard(equipo: Equipo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Serial: ${equipo.serial}")
            Text("Placa: ${equipo.placa}")
            Text("Tipo: ${equipo.tipoEquipo?.nombre ?: "Sin tipo"}")
            Text("Marca: ${equipo.marca?.nombre ?: "Sin marca"}")
            Text("Estado: ${equipo.estado?.nombre ?: "Sin estado"}")
        }
    }
}
