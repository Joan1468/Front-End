package com.udec.cajica.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.navigation.Screen // ✅ Cambia AppScreen por Screen

/**
 * Pantalla que muestra la lista de equipos.
 */
@Composable
fun ListaEquiposScreen(
    navController: NavHostController,
    equipos: List<Equipo>,
    onBuscarEquipoClick: () -> Unit // ✅ Agrega este parámetro si lo necesitas
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Equipos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(equipos) { equipo ->
                EquipoItem(
                    equipo = equipo,
                    onClick = {

                    }
                )
            }
        }
    }
}

/**
 * Item que representa un equipo dentro de la lista.
 */
@Composable
fun EquipoItem(equipo: Equipo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = equipo.idEquipo.toString(),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Serial: ${equipo.serial ?: "Sin serial"}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Placa: ${equipo.placa ?: "Sin placa"}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Tipo: ${equipo.tipoEquipo ?: "Sin tipo"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}