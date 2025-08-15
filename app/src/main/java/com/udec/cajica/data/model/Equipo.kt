package com.udec.cajica.data.model

data class Equipo(
    val idEquipo: Long,
    val serial: String,
    val placa: String,
    val tipoEquipo: TipoEquipo,
    val marca: Marca?,
    val estado: Estado?
)

data class TipoEquipo(
    val idTipoEquipo: Long,
    val nombre: String
)

data class Marca(
    val idMarca: Long,
    val nombre: String
)

data class Estado(
    val idEstado: Long,
    val nombre: String
)

// ✅ ✅ ✅ AGREGADO: EquipoDto para usar al crear o enviar al backend
data class EquipoDto(
    val idEquipo: Long? = null,
    val serial: String,
    val placa: String,
    val idTipoEquipo: Long,
    val idMarca: Long,
    val idEstado: Long,
    val idInventario: Long
)
