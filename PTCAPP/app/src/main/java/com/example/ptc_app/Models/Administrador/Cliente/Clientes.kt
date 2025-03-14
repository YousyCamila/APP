package com.example.ptc_app.Models.Administrador.Cliente

import Persona.Personas
import com.example.ptc_app.Models.Administrador.Caso.Caso

class Clientes(val personas: Personas) {

    private val casos = mutableListOf<Caso>()

    private fun datosCliente() {
        println("Id ${personas.id}  ")
        println("Cliente ${personas.nombre}  ")
        println("Celular: ${personas.celular}")
        println("Dirección: ${personas.direccion}")
        println("Correo: ${personas.correo}")

    }
    fun agregarCaso(caso: Caso) {
        casos.add(caso)
    }

    companion object {

        val clientes = mutableListOf(

            Clientes(Personas("1", "Danna Camila", 321269946, "Crr 17H BIS", "dcami@gmail.com")),
            Clientes(Personas("2", "Oriana Guerra", 321264566, "Crr 18H BIS", "ori@gmail.com")),
            Clientes(
                Personas(
                    "3",
                    "Martin Emilio",
                    311600120,
                    "Crr 25H BIS",
                    "martin@gmail.com"
                )
            )
        )


    }

}