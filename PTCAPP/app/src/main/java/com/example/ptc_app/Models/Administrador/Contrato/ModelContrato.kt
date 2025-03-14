package com.example.ptc_app.Models.Administrador.Contrato

import com.example.ptc_app.Models.Administrador.Cliente.Clientes
import java.util.Date

class ModelContrato(
    private var descripcionServicio: String,
    private var fechaInicio: Date,
    private var fechaCierre: Date,
    private var clausulas: String,
    private var tarifa: Float,
    private var estado: Boolean,
    private var cliente: Clientes,
    private val fechaCreacion: Date = Date()
) {
    // Getters
    fun getDescripcionServicio() = descripcionServicio
    fun getFechaInicio() = fechaInicio
    fun getFechaCierre() = fechaCierre
    fun getClausulas() = clausulas
    fun getTarifa() = tarifa
    fun getEstado() = estado
    fun getCliente() = cliente
    fun getFechaCreacion()= fechaCreacion
}