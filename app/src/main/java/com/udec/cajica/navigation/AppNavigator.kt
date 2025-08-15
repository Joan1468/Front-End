package com.udec.cajica.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.udec.cajica.ui.screens.equipos.DetalleEquipoScreen
import com.udec.cajica.ui.screens.equipos.ListaEquiposScreen

sealed class AppScreen(val route: String) {
    object ListaEquipos : AppScreen("lista_equipos")
    object DetalleEquipo : AppScreen("detalle_equipo/{equipoId}") {
        fun createRoute(equipoId: String) = "detalle_equipo/$equipoId"
    }
}

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.ListaEquipos.route
    ) {
        composable(AppScreen.ListaEquipos.route) {
            ListaEquiposScreen(navController)
        }

        composable(AppScreen.DetalleEquipo.route) { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId") ?: ""
            DetalleEquipoScreen(navController, equipoId)
        }
    }
}
