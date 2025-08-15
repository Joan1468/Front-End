package com.udec.cajica.ui.screens // ✅ Ajuste correcto: la ruta debe coincidir con la carpeta real

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
import com.udec.cajica.data.model.Equipo // ✅ Ajuste correcto para la ruta del modelo

/**
 * Pantalla que muestra la lista de equipos.
 *
 * @param equipos Lista de objetos Equipo que se mostrarán.
 * @param onEquipoClick Acción que se ejecuta cuando se hace clic en un equipo.
 */
@Composable
fun ListaEquiposScreen(
    equipos: List<Equipo>,
    onEquipoClick: (Equipo) -> Unit
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
            modifier = Modifier.fillMaxSize() // ✅ Añadido para ocupar todo el espacio
        ) {
            items(equipos) { equipo ->
                EquipoItem(equipo = equipo, onClick = { onEquipoClick(equipo) })
            }
        }
    }
}

/**
 * Item que representa un equipo dentro de la lista.
 *
 * @param equipo Objeto Equipo que se mostrará.
 * @param onClick Acción cuando se toca el item.
 */
@Composable
fun EquipoItem(equipo: Equipo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp), // ✅ Mantengo padding vertical
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // ✅ Conversión a String para evitar errores si idEquipo no es String
            Text(
                text = equipo.idEquipo.toString(),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            // ✅ Si serial puede ser null, usamos el operador ?: para evitar crash
            Text(
                text = "Serial: ${equipo.serial ?: "Sin serial"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
