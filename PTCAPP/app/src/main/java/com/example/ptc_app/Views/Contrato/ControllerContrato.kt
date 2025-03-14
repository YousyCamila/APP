package com.example.ptc_app.Views.Contrato

import com.example.ptc_app.Models.Administrador.Cliente.Clientes
import com.example.ptc_app.Models.Administrador.Contrato.ModelContrato
import Persona.Personas
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ControllerContrato {
    companion object {
        val listaModelContratoes = mutableListOf<ModelContrato>()
    }

    fun agregarContrato(
        clienteSeleccionado: Clientes,
        descripcion: String,
        fechaInicioStr: String,
        fechaCierreStr: String,
        clausulas: String,
        tarifa: Float,
        estado: Boolean
    ): String {

        val fechaInicio = convertirFecha(fechaInicioStr)
        val fechaCierre = convertirFecha(fechaCierreStr)


        val nuevoModelContrato = ModelContrato(
            descripcion,
            fechaInicio,
            fechaCierre,
            clausulas,
            tarifa,
            estado,
            clienteSeleccionado
        )


        listaModelContratoes.add(nuevoModelContrato)


        return "Contrato creado con éxito para el cliente ${clienteSeleccionado.personas.nombre}."
    }
    fun eliminarContrato(indice: Int): String {
        if (indice in 0 until listaModelContratoes.size) {
            val contrato = listaModelContratoes[indice]
            val fechaCreacion = contrato.getFechaCreacion()
            val ahora = Date()
            val diferenciaTiempo = ahora.time - fechaCreacion.time

            return if (diferenciaTiempo <= 5 * 60 * 1000) {
                listaModelContratoes.removeAt(indice)
                " Contrato del cliente ${contrato.getCliente().personas.nombre} eliminado correctamente."
            } else {
                " No se puede eliminar el contrato. Han pasado más de 5 minutos desde su creación."
            }
        } else {
            return " Índice inválido. No se pudo eliminar el contrato."
        }
    }


    private fun convertirFecha(fechaStr: String): Date {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            formato.parse(fechaStr) ?: Date()
        } catch (e: Exception) {
            Date()
        }
    }
}