package com.example.ptc_app

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ptc_app.Models.Administrador.Cliente.Clientes
import Persona.Personas
import com.example.ptc_app.Views.Contrato.ControllerContrato
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var clienteDropdown: AutoCompleteTextView
    private var clienteSeleccionado: Clientes? = null // Objeto Clientes

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Formato adecuado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar elementos
        clienteDropdown = findViewById(R.id.clienteDropdown)
        configurarDropdownClientes()
        configurarSelectorFecha(R.id.fechaInicio)
        configurarSelectorFecha(R.id.fechaCierre)

        // Botón guardar contrato
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        btnGuardar.setOnClickListener { crearContrato(it) }

        // Botón eliminar contrato
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        btnEliminar.setOnClickListener { eliminarContrato(it) }
    }

    private fun configurarDropdownClientes() {
        // Obtener los nombres de la lista de clientes
        val nombresClientes = Clientes.clientes.map { it.personas.nombre }

        // Adaptador para el Dropdown
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, nombresClientes)
        clienteDropdown.setAdapter(adapter)

        // Cuando seleccionan un cliente
        clienteDropdown.setOnItemClickListener { _, _, position, _ ->
            clienteSeleccionado = Clientes.clientes[position] // Guardas el cliente completo
        }
    }


    private fun configurarSelectorFecha(viewId: Int) {
        val editText = findViewById<TextInputEditText>(viewId)

        editText.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecciona una fecha")
                .build()

            datePicker.show(supportFragmentManager, "DATE_PICKER")

            datePicker.addOnPositiveButtonClickListener { selection ->
                val selectedDate = dateFormat.format(Date(selection))
                editText.setText(selectedDate)
            }
        }
    }

    // Crear contrato
    fun crearContrato(v: View) {
        val descripcion = findViewById<TextInputEditText>(R.id.descripcion).text.toString()
        val fechaInicioStr = findViewById<TextInputEditText>(R.id.fechaInicio).text.toString()
        val fechaCierreStr = findViewById<TextInputEditText>(R.id.fechaCierre).text.toString()
        val tarifaStr = findViewById<TextInputEditText>(R.id.tarifa).text.toString()
        val clausulas = findViewById<TextInputEditText>(R.id.clausulas).text.toString()

        if (descripcion.isEmpty() || fechaInicioStr.isEmpty() || fechaCierreStr.isEmpty() ||
            tarifaStr.isEmpty() || clausulas.isEmpty() || clienteSeleccionado == null) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val tarifa = tarifaStr.toFloatOrNull()
        if (tarifa == null) {
            Toast.makeText(this, "La tarifa debe ser un número válido", Toast.LENGTH_SHORT).show()
            return
        }

        // Llamar al ControllerContrato para agregar
        val resultado = ControllerContrato().agregarContrato(
            clienteSeleccionado = clienteSeleccionado!!, // Ya validado que no es null
            descripcion = descripcion,
            fechaInicioStr = fechaInicioStr,
            fechaCierreStr = fechaCierreStr,
            clausulas = clausulas,
            tarifa = tarifa,
            estado = true // Contrato activo al crear
        )

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
        limpiarCampos()
    }

    // Eliminar contrato por índice
    fun eliminarContrato(v: View) {
        val indiceStr = findViewById<TextInputEditText>(R.id.indiceEliminar).text.toString()

        val indice = indiceStr.toIntOrNull()
        if (indice == null) {
            Toast.makeText(this, "Índice inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = ControllerContrato().eliminarContrato(indice)
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()
    }

    // Limpiar campos después de guardar
    private fun limpiarCampos() {
        findViewById<TextInputEditText>(R.id.descripcion).setText("")
        findViewById<TextInputEditText>(R.id.fechaInicio).setText("")
        findViewById<TextInputEditText>(R.id.fechaCierre).setText("")
        findViewById<TextInputEditText>(R.id.tarifa).setText("")
        findViewById<TextInputEditText>(R.id.clausulas).setText("")
        findViewById<TextInputEditText>(R.id.indiceEliminar).setText("")
        clienteDropdown.setText("")
        clienteSeleccionado = null
    }
}
