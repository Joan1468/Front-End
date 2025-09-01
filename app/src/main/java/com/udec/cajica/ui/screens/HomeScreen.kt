package com.udec.cajica.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.udec.cajica.R

@Composable
fun HomeScreen(navController: NavController) {
    // Colores corporativos de la Alcaldía de Cajicá (verde y blanco)
    val primaryColor = Color(0xFF0F4C2D) // Verde oscuro corporativo
    val secondaryColor = Color(0xFF8CC63E) // Verde claro
    val backgroundColor = Color(0xFFF5F5F5) // Fondo gris claro

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // Encabezado con gradiente y logo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(primaryColor, secondaryColor)
                    )
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo (debes reemplazar con el logo real de la Alcaldía)


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Alcaldía de Cajicá",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Sistema de Gestión de Inventarios",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 180.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjeta de bienvenida
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido al Sistema de Gestión",
                        color = primaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Selecciona una opción para continuar",
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Botones de acciones principales
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    icon = Icons.Default.Upload,
                    text = "Subir Archivo Excel",
                    onClick = { navController.navigate("subir_excel") },
                    color = primaryColor
                )

                ActionButton(
                    icon = Icons.Default.Add,
                    text = "Crear Equipo",
                    onClick = { navController.navigate("crearEquipo") },
                    color = primaryColor
                )

                ActionButton(
                    icon = Icons.Default.Search,
                    text = "Buscar Equipo",
                    onClick = { navController.navigate("buscarEquipo") },
                    color = primaryColor
                )

                ActionButton(
                    icon = Icons.Default.Delete,
                    text = "Eliminar Equipo",
                    onClick = { navController.navigate("eliminarEquipo") },
                    color = Color(0xFFD32F2F) // Rojo para acción destructiva
                )

                ActionButton(
                    icon = Icons.Default.QrCodeScanner,
                    text = "Escanear QR",
                    onClick = { navController.navigate("escanearQR") },
                    color = primaryColor
                )
            }

            // Pie de página
            Text(
                text = "Alcaldía Municipal de Cajicá - 2024",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, text: String, onClick: () -> Unit, color: Color) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}