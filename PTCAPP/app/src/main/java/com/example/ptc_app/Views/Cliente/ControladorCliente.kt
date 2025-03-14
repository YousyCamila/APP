package com.example.ptc_app.Views.Cliente

import com.example.ptc_app.Views.Detective.FunAdministrador
import Persona.Personas
import com.example.ptc_app.Models.Administrador.Cliente.Clientes

class ControladorCliente : FunAdministrador() {

    override fun crear() {
        print("Ingrese el id")
        val idCliente = readln()

        print("Ingrese el nombre del cliente: ")
        val nomCliente = readln()

        print("Ingrese el número de celular: ")
        val celCliente = readln().toIntOrNull() ?: run {
            println("Número inválido. Intente de nuevo.")
            return
        }

        print("Ingrese la dirección: ")
        val direccCliente = readln()

        print("Ingrese el correo: ")
        val correoCliente = readln()

        val nuevoCliente =
            Clientes(Personas(idCliente, nomCliente, celCliente, direccCliente, correoCliente))
        Clientes.Companion.clientes.add(nuevoCliente)

        println("Cliente agregado exitosamente.")
    }

    override fun editar() {
        if (Clientes.Companion.clientes.isEmpty()) {
            println("No hay clientes para editar.")
            return
        }

        println("Seleccione el cliente a editar:")
        Clientes.Companion.clientes.forEachIndexed { index, cliente ->
            println("${index + 1}. ${cliente.personas.nombre}")
        }

        val seleccion = readln().toIntOrNull()
        if (seleccion == null || seleccion !in 1..Clientes.Companion.clientes.size) {
            println("Selección inválida.")
            return
        }

        val clienteSeleccionado = Clientes.Companion.clientes[seleccion - 1]
        println("Editando cliente: ${clienteSeleccionado.personas.nombre}")

        println("1. Nombre")
        println("2. Celular")
        println("3. Dirección")
        println("4. Correo")
        print("Seleccione el campo a editar: ")

        when (readln()) {
            "1" -> {
                print("Ingrese el nuevo nombre: ")
                clienteSeleccionado.personas.nombre = readln()
            }
            "2" -> {
                print("Ingrese el nuevo celular: ")
                clienteSeleccionado.personas.celular = readln().toIntOrNull() ?: return println("Número inválido.")
            }
            "3" -> {
                print("Ingrese la nueva dirección: ")
                clienteSeleccionado.personas.direccion = readln()
            }
            "4" -> {
                print("Ingrese el nuevo correo: ")
                clienteSeleccionado.personas.correo = readln()
            }
            else -> println("Opción inválida.")
        }

        println("Cliente actualizado correctamente.")
    }

    override fun eliminar() {
        if (Clientes.Companion.clientes.isEmpty()) {
            println("No hay clientes para eliminar.")
            return
        }

        println("Seleccione el cliente a eliminar:")
        Clientes.Companion.clientes.forEachIndexed { index, cliente ->
            println("${index + 1}. ${cliente.personas.nombre}")
        }

        val seleccion = readln().toIntOrNull()
        if (seleccion == null || seleccion !in 1..Clientes.Companion.clientes.size) {
            println("Selección inválida.")
            return
        }

        val clienteSeleccionado = Clientes.Companion.clientes[seleccion - 1]
        println("¿Está seguro de eliminar a ${clienteSeleccionado.personas.nombre}? (1: Sí, 2: No)")

        when (readln()) {
            "1" -> {
                Clientes.Companion.clientes.removeAt(seleccion - 1)
                println("Cliente eliminado exitosamente.")
            }
            "2" -> println("Operación cancelada.")
            else -> println("Opción inválida.")
        }
    }

    fun mostrarClientes() {
        if (Clientes.Companion.clientes.isEmpty()) {
            println("No hay clientes registrados.")
        } else {
            println("\n=== Lista de Clientes ===")
            Clientes.Companion.clientes.forEachIndexed { index, cliente ->
                println("${index + 1}. ${cliente.personas.id} - ${cliente.personas.nombre} - ${cliente.personas.celular} - ${cliente.personas.direccion} - ${cliente.personas.correo}")
            }
        }
    }
}