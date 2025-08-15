package com.udec.cajica.data.api

import com.udec.cajica.data.model.Usuario
import com.udec.cajica.data.model.UsuarioDto
import com.udec.cajica.data.model.Equipo
import com.udec.cajica.data.model.EquipoDto

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @GET("/usuario")
    suspend fun listarUsuarios(): List<Usuario>

    @POST("/usuario/guardarDto")
    suspend fun guardarUsuario(@Body usuarioDto: UsuarioDto): Usuario

    @GET("/usuario/{id}")
    suspend fun buscarUsuarioPorId(@Path("id") id: Long): Usuario

    @DELETE("/usuario/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Long): Response<Unit>

    // ==============================
    // ✅ FUNCIONES PARA EQUIPO
    // ==============================

    @GET("/equipo")
    suspend fun listarEquipos(): List<Equipo>

    @POST("/equipo")
    suspend fun guardarEquipo(@Body equipo: EquipoDto): Equipo

    @GET("/equipo/{id}")
    suspend fun buscarEquipoPorId(@Path("id") id: Long): Equipo

    @DELETE("/equipo/{id}")
    suspend fun eliminarEquipo(@Path("id") id: Long): Response<Unit>

    // ==============================
    // ✅ FUNCIÓN PARA SUBIR ARCHIVO EXCEL
    // ==============================

    @Multipart
    @POST("/archivo/excel")
    suspend fun subirArchivoExcel(@Part archivo: MultipartBody.Part): Response<Void>
}
