package com.udec.cajica.data.repository

import com.udec.cajica.data.model.Equipo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipoRepository @Inject constructor() {
    suspend fun eliminarEquipo(id: Long) {
        // Aquí va la lógica para eliminar en tu base de datos o API
    }

    suspend fun guardarEquipo(equipo: Equipo) {
        // Aquí va la lógica para guardar en tu base de datos o API
    }
}
