package com.udec.cajica.data.repository

import com.udec.cajica.data.api.ApiService
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.data.model.EquipoDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipoRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun listarEquipos(): Result<List<Equipo>> = try {
        Result.success(apiService.listarEquipos())
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun buscarEquipoPorId(id: Long): Result<Equipo> = try {
        Result.success(apiService.buscarEquipoPorId(id))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun guardarEquipo(equipo: EquipoDto): Result<Equipo> = try {
        Result.success(apiService.guardarEquipo(equipo))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun eliminarEquipo(id: Long): Result<Boolean> = try {
        val response = apiService.eliminarEquipo(id)
        Result.success(response.isSuccessful)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun agregarEquipo(equipo: Equipo) {

    }
}
