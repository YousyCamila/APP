package com.example.ptc_app.Views.Caso

import com.example.ptc_app.Models.Administrador.Caso.Caso
import com.example.ptc_app.Models.Administrador.Cliente.Clientes
import com.example.ptc_app.Models.Administrador.Detective.Detectives

class CasoService {
    private val nombresPermitidos = mapOf(
        1 to "Cadena de custodia",
        2 to "Investigación de extorsión",
        3 to "Estudios de seguridad",
        4 to "Investigación de infidelidades",
        5 to "Investigación de robos empresariales",
        6 to "Antecedentes",
        7 to "Recuperación de vehículos"
    )

    fun crearCaso(datos: Caso): Caso? {
        if (BaseDatosTemporal.casos.any { it.id == datos.id }) {
            println("Error: Ya existe un caso con el ID ${datos.id}")
            return null
        }

        // Buscar cliente y detective por su ID
        val cliente = Clientes.Companion.clientes.firstOrNull { it.personas.id == datos.idCliente }
        val detective = Detectives.Companion.listaDetectives.firstOrNull { it.personas.id == datos.idDetective }

        if (cliente == null || detective == null) {
            println("Error: Cliente o detective no encontrados.")
            return null
        }

        return datos.apply {
            BaseDatosTemporal.casos.add(this)
            cliente.agregarCaso(this)
            detective.agregarCaso(this)
        }
    }

    fun obtenerCasosPorEmailCliente(emailCliente: String): List<Caso> {
        val cliente = Clientes.Companion.clientes.firstOrNull { it.personas.correo == emailCliente }
            ?: throw IllegalArgumentException("No se encontró un cliente con el email: $emailCliente")

        return BaseDatosTemporal.casos.filter { it.idCliente == cliente.personas.id }
    }

    fun obtenerCasosPorEmailDetective(emailDetective: String): List<Caso> {
        val detective = Detectives.Companion.listaDetectives.firstOrNull { it.personas.correo == emailDetective }
            ?: throw IllegalArgumentException("No se encontró un detective con el email: $emailDetective")

        return detective.casos
    }

    fun listarCasos(): List<Caso> {
        return BaseDatosTemporal.casos.takeIf { it.isNotEmpty() }
            ?: throw IllegalStateException("No hay casos registrados actualmente.")
    }

    fun buscarCasoPorId(id: String): Caso {
        return BaseDatosTemporal.casos.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Caso no encontrado")
    }

    fun desactivarCaso(id: String): Caso {
        val caso = buscarCasoPorId(id)

        if (!caso.activo) {
            println("El caso con ID $id ya está desactivado.")
            return caso
        }

        return caso.apply { activo = false }
    }
}