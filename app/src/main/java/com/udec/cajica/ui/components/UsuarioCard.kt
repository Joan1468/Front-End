package com.udec.cajica.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.udec.cajica.data.model.Usuario

@Composable
fun UsuarioCard(
    usuario: Usuario,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.large,
        onClick = onClick ?: {}
    ) {
        Column(Modifier.padding(20.dp)) {
            Text(
                text = usuario.nombre,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Correo: ${usuario.correo}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Tipo de documento: ${usuario.tipoDocumento?.nombre ?: "Sin documento"}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                // ✅ Editado: validamos nulos y mostramos "Sin vinculación" si es null
                text = "Vinculación: ${usuario.tipoVinculacion?.vinculacion ?: "Sin vinculación"}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                // ✅ Editado: validamos nulos y mostramos "Sin secretaría" si es null
                text = "Secretaría: ${usuario.secretarias?.nombre ?: "Sin secretaría"}",

                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
