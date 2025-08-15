package com.udec.cajica.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.data.repository.EquipoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipoViewModel @Inject constructor(
    private val equipoRepository: EquipoRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // ✅ Método para eliminar
    fun eliminarEquipo(id: Long) {
        viewModelScope.launch {
            try {
                equipoRepository.eliminarEquipo(id)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    // ✅ Método para guardar (crear)
    fun guardarEquipo(equipo: Equipo) {
        viewModelScope.launch {
            try {
                equipoRepository.guardarEquipo(equipo)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
