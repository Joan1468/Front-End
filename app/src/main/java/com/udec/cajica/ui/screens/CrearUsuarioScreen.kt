package com.udec.cajica.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udec.cajica.data.model.UsuarioDto
import com.udec.cajica.viewModel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearUsuarioScreen(
    navController: NavHostController,
    viewModel: UsuarioViewModel
) {
    var idUsuario by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var tipoDocumentoId by remember { mutableStateOf("") }
    var tipoVinculacionId by remember { mutableStateOf("") }
    var secretariaId by remember { mutableStateOf("") }

    val creando = viewModel.creando
    val crearExitoso = viewModel.crearExitoso
    val error = viewModel.error

    if (crearExitoso == true) {
        LaunchedEffect(Unit) {
            viewModel.resetCrearUsuario()
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Crear Usuario",
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
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // CAMPO: ID Usuario (Cédula)
                OutlinedTextField(
                    value = idUsuario,
                    onValueChange = { idUsuario = it },
                    label = { Text("ID Usuario (Cédula)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = tipoDocumentoId,
                    onValueChange = { tipoDocumentoId = it },
                    label = { Text("ID Tipo de Documento") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = tipoVinculacionId,
                    onValueChange = { tipoVinculacionId = it },
                    label = { Text("ID Tipo de Vinculación") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = secretariaId,
                    onValueChange = { secretariaId = it },
                    label = { Text("ID Secretaría") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.guardarUsuario(
                            UsuarioDto(
                                id_usuario = idUsuario.toLongOrNull() ?: 0L, // ← Aquí convertimos
                                nombre = nombre,
                                correo = correo,
                                idTipoDocumento = tipoDocumentoId.toInt(),
                                idTipoVinculacion = tipoVinculacionId.toInt(),
                                idSecretaria = secretariaId.toInt()
                            )
                        )
                    },
                    enabled = !creando &&
                            idUsuario.isNotBlank() &&
                            nombre.isNotBlank() &&
                            correo.isNotBlank() &&
                            tipoDocumentoId.isNotBlank() &&
                            tipoVinculacionId.isNotBlank() &&
                            secretariaId.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (creando) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                    Text("Crear Usuario")
                }

                if (error != null && crearExitoso == false) {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
