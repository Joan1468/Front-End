package com.udec.cajica.navigation

sealed class Screen(val route: String) {
    object ListaEquipos : Screen("listaEquipos")
    object CrearEquipo : Screen("crearEquipo")
    object BuscarEquipo : Screen("buscarEquipo")
}
