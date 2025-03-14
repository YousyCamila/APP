package com.example.ptc_app.Views.Detective

import Persona.Personas
import com.example.ptc_app.Models.Administrador.Detective.Detectives

class ControladorDetective : FunAdministrador() {

    override fun crear() {

        print("Ingrese el id del detective: ")
        val idDetective = readln()

        print("Ingrese el nombre del detective: ")
        val nomDetective = readln()

        println("Ingrese el número de celular: ")
        val celDetective = readln().toIntOrNull() ?: run {
            println("Número inválido. Intente de nuevo.")
            return
        }

        print("Ingrese la dirección: ")
        val direccDetective = readln()

        print("Ingrese el correo: ")
        val correoDetective = readln()

        val nuevoDetective = Detectives(
            Personas(
                idDetective,
                nomDetective,
                celDetective,
                direccDetective,
                correoDetective
            )
        )
        Detectives.Companion.listaDetectives.add(nuevoDetective)

        println("Detective agregado exitosamente.")
    }

    override fun editar() {
        if (Detectives.Companion.listaDetectives.isEmpty()) {
            println("No hay detectives para editar.")
            return
        }

        println("Seleccione el detective a editar:")
        Detectives.Companion.listaDetectives.forEachIndexed { index, detective ->
            println("${index + 1}. ${detective.personas.nombre}")
        }

        val seleccion = readln().toIntOrNull()
        if (seleccion == null || seleccion !in 1..Detectives.Companion.listaDetectives.size) {
            println("Selección inválida.")
            return
        }

        val detectiveSeleccionado = Detectives.Companion.listaDetectives[seleccion - 1]
        println("Editando detective: ${detectiveSeleccionado.personas.nombre}")
        println("1. Id")
        println("2. Nombre")
        println("3. Celular")
        println("4. Dirección")
        println("5. Correo")
        print("Seleccione el campo a editar: ")

        when (readln()) {
            "1" -> {
                print("Ingrese el id ")
                detectiveSeleccionado.personas.id = readln()
            }
            "2" -> {
                print("Ingrese el nuevo nombre: ")
                detectiveSeleccionado.personas.nombre = readln()
            }
            "3" -> {
                print("Ingrese el nuevo celular: ")
                detectiveSeleccionado.personas.celular = readln().toIntOrNull() ?: return println("Número inválido.")
            }
            "4" -> {
                print("Ingrese la nueva dirección: ")
                detectiveSeleccionado.personas.direccion = readln()
            }
            "5" -> {
                print("Ingrese el nuevo correo: ")
                detectiveSeleccionado.personas.correo = readln()
            }
            else -> println("Opción inválida.")
        }

        println("Detective actualizado correctamente.")
    }

    override fun eliminar() {
        if (Detectives.Companion.listaDetectives.isEmpty()) {
            println("No hay detectives para eliminar.")
            return
        }

        println("Seleccione el detective a eliminar:")
        Detectives.Companion.listaDetectives.forEachIndexed { index, detective ->
            println("${index + 1}. ${detective.personas.nombre}")
        }

        val seleccion = readln().toIntOrNull()
        if (seleccion == null || seleccion !in 1..Detectives.Companion.listaDetectives.size) {
            println("Selección inválida.")
            return
        }

        val detectiveSeleccionado = Detectives.Companion.listaDetectives[seleccion - 1]
        println("¿Está seguro de eliminar a ${detectiveSeleccionado.personas.nombre}? (1: Sí, 2: No)")

        when (readln()) {
            "1" -> {
                Detectives.Companion.listaDetectives.removeAt(seleccion - 1)
                println("Detective eliminado exitosamente.")
            }
            "2" -> println("Operación cancelada.")
            else -> println("Opción inválida.")
        }
    }

    fun mostrarDetectives() {
        if (Detectives.Companion.listaDetectives.isEmpty()) {
            println("No hay detectives registrados.")
        } else {
            println("\n=== Lista de Detectives ===")
            Detectives.Companion.listaDetectives.forEachIndexed { index, detective ->
                println("${index + 1}. ${detective.personas.id} - ${detective.personas.nombre} - ${detective.personas.celular} - ${detective.personas.direccion} - ${detective.personas.correo}")
            }
        }
    }
}