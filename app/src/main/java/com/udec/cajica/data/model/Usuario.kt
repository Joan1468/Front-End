package com.udec.cajica.data.model

data class UsuarioDto(
    val id_usuario: Long, // Aquí ingresas tú la cédula manualmente
    val nombre: String,
    val correo: String,
    val idTipoDocumento: Int,
    val idTipoVinculacion: Int,
    val idSecretaria: Int
)

data class Usuario(
    val id_usuario: Int?,
    val nombre: String,
    val correo: String,
    val tipoDocumento: TipoDocumento,
    val tipoVinculacion: TipoVinculacion,
    val secretarias: Secretaria
)

data class TipoDocumento(
    val id_tipo_documento: Int,
    val nombre: String
)

data class TipoVinculacion(
    val id_tipo_vinculacion: Int,
    val vinculacion: String
)

data class Secretaria(
    val id_secretaria: Int,
    val nombre: String
)
