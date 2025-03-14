package com.example.ptc_app.Models.Administrador.Detective

import Persona.Personas
import com.example.ptc_app.Models.Administrador.Caso.Caso

class Detectives(val personas: Personas) {

    val casos = mutableListOf<Caso>()

    private fun datosDetective() {

        println("Id ${personas.id}  ")
        println("Detective ${personas.nombre}  ")
        println("Celular: ${personas.celular}")
        println("Dirección: ${personas.direccion}")
        println("Correo: ${personas.correo}")
    }
    fun agregarCaso(caso: Caso) {
        casos.add(caso)
    }

    companion object {
        val listaDetectives = mutableListOf(
            Detectives(
                Personas(
                    "1",
                    "Ludwig Smirh",
                    321269946,
                    "calle 25 s",
                    "ludwig@gmail.com"
                )
            ),
            Detectives(
                Personas(
                    "2",
                    "Wolfgang Steve",
                    321264566,
                    "calle 60 n",
                    "wolfgang@gmail.com"
                )
            ),
            Detectives(
                Personas(
                    "3",
                    "Cristian Cardona",
                    311600120,
                    "avenida el dorado",
                    "cristian@gmail.com"
                )
            )
        )
    }
}