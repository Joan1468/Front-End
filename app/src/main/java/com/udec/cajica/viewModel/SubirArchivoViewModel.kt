package com.udec.cajica.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udec.cajica.data.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SubirArchivoViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun subirArchivoExcel(file: File, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        val requestFile = file
            .asRequestBody("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".toMediaTypeOrNull())

        val body = MultipartBody.Part.createFormData("archivo", file.name, requestFile)

        viewModelScope.launch {
            try {
                val response = apiService.subirArchivoExcel(body)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError(Exception("Error en la subida: ${response.code()}"))
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}
