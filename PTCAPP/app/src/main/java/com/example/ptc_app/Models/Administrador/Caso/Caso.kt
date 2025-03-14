package com.example.ptc_app.Models.Administrador.Caso

data class Caso(
    val id: String,
    val nombreCaso: String,
    val idCliente: String,
    val idDetective: String,
    var activo: Boolean = true


)