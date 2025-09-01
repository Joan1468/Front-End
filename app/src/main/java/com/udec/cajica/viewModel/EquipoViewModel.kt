package com.udec.cajica.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.data.model.EquipoDto
import com.udec.cajica.data.repository.EquipoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Suppress("MemberVisibilityCanBePrivate", "unused")
class EquipoViewModel @Inject constructor(
    private val equipoRepository: EquipoRepository
) : ViewModel() {

    private val _equipos = MutableStateFlow<List<Equipo>>(emptyList())
    val equipos: StateFlow<List<Equipo>> = _equipos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    /** Cargar todos los equipos desde el backend */
    fun fetchEquipos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                equipoRepository.listarEquipos().fold(
                    onSuccess = { lista ->
                        _equipos.value = lista
                        _error.value = null
                    },
                    onFailure = { ex ->
                        _error.value = ex.message ?: "Error al listar equipos."
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** Crear un nuevo equipo y agregarlo a la lista local */
    fun createEquipo(equipoDto: EquipoDto) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                equipoRepository.guardarEquipo(equipoDto).fold(
                    onSuccess = { creado ->
                        _equipos.value += creado // operador compuesto optimizado
                        _error.value = null
                    },
                    onFailure = { ex ->
                        _error.value = ex.message ?: "Error al guardar equipo."
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** Buscar un equipo por ID y actualizarlo en la lista */
    fun buscarEquipoPorId(id: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                equipoRepository.buscarEquipoPorId(id).fold(
                    onSuccess = { equipo ->
                        _equipos.value = _equipos.value
                            .filterNot { it.idEquipo == equipo.idEquipo } + equipo
                        _error.value = null
                    },
                    onFailure = { ex ->
                        _error.value = ex.message ?: "Error al buscar equipo."
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** Eliminar un equipo por ID */
    fun eliminarEquipo(id: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                equipoRepository.eliminarEquipo(id).fold(
                    onSuccess = { ok ->
                        if (ok) {
                            _equipos.value = _equipos.value.filterNot { it.idEquipo == id }
                            _error.value = null
                        } else {
                            _error.value = "No se pudo eliminar el equipo."
                        }
                    },
                    onFailure = { ex ->
                        _error.value = ex.message ?: "Error al eliminar equipo."
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun agregarEquipo(equipo: Equipo) {
        viewModelScope.launch {
            equipoRepository.agregarEquipo(equipo) // ✅ Este método debe existir en el repositorio
        }

    /** Limpia el error actual */
    fun clearError() {
        _error.value = null
    }
}}
