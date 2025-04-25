package com.example.navegacion.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Cambiar el tipo de registro para mascotas
class RegistroViewModel : ViewModel() {

    // Lista de registros de mascotas
    private val _registros = MutableStateFlow<List<Triple<String, String, String>>>(emptyList())
    val registros: StateFlow<List<Triple<String, String, String>>> = _registros

    // Función para agregar una nueva mascota
    fun agregarRegistro(nombre: String, raza: String, edad: String) {
        val nuevaMascota = Triple(nombre, raza, edad)
        // Asegurarse de que no se agregue la misma mascota
        if (!registros.value.contains(nuevaMascota)) {
            _registros.value = _registros.value + nuevaMascota
        }
    }

    // Función para eliminar una mascota
    fun eliminarRegistro(mascota: Triple<String, String, String>) {
        // Eliminamos el registro de la lista
        _registros.value = _registros.value.toMutableList().apply { remove(mascota) }
    }

    // Función para editar una mascota
    fun editarRegistro(mascotaAntigua: Triple<String, String, String>, nuevoNombre: String, nuevaRaza: String, nuevaEdad: String) {
        // Actualizamos el registro en la lista con los nuevos datos
        _registros.value = _registros.value.map {
            if (it == mascotaAntigua) {
                Triple(nuevoNombre, nuevaRaza, nuevaEdad) // Cambiamos el registro
            } else {
                it // Mantenemos el resto igual
            }
        }
    }
}
