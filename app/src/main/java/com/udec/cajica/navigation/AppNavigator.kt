package com.udec.cajica.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.udec.cajica.ui.screens.BuscarEquipoScreen
import com.udec.cajica.ui.screens.CrearEquipoScreen
import com.udec.cajica.ui.screens.HomeScreen
import com.udec.cajica.ui.screens.ListaEquiposScreen
import com.udec.cajica.viewModel.EquipoViewModel


@Composable
fun AppNavigator(navController: NavHostController, viewModel: EquipoViewModel) {
    NavHost(navController = navController, startDestination = "home") {

        // Pantalla principal (Home)
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Pantalla de lista de equipos
        composable(Screen.ListaEquipos.route) {
            val equiposState = viewModel.equipos.collectAsState() // StateFlow -> State
            ListaEquiposScreen(
                navController = navController,
                equipos = equiposState.value,
                onBuscarEquipoClick = {
                    navController.navigate(Screen.BuscarEquipo.route)
                }
            )
        }

        // Pantalla de creación de equipo
        composable(Screen.CrearEquipo.route) {
            CrearEquipoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Pantalla de búsqueda de equipo
        composable(Screen.BuscarEquipo.route) {
            BuscarEquipoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Aquí puedes agregar más pantallas: EliminarEquipo, EscanearQR
    }
}
