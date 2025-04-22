package com.example.mascotas_registro_para_generar_carnet.Screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenA(navController: NavController) {
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var raza by remember { mutableStateOf(TextFieldValue("")) }
    var tamaño by remember { mutableStateOf(TextFieldValue("")) }
    var edad by remember { mutableStateOf(TextFieldValue("")) }
    var foto by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Ingrese los datos de la mascota", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text("Raza") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = tamaño,
            onValueChange = { tamaño = it },
            label = { Text("Tamaño") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = foto,
            onValueChange = { foto = it },
            label = { Text("URL de la Foto : ") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                // Verifica si todos los campos están completos
                if (nombre.text.isNotEmpty() && raza.text.isNotEmpty() && tamaño.text.isNotEmpty() && edad.text.isNotEmpty() && foto.text.isNotEmpty()) {
                    val route = "screen_b/" +
                            "${Uri.encode(nombre.text)}/" +
                            "${Uri.encode(raza.text)}/" +
                            "${Uri.encode(tamaño.text)}/" +
                            "${Uri.encode(edad.text)}/" +
                            "${Uri.encode(foto.text)}"
                    navController.navigate(route)
                } else {
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Enviar")
        }
    }
}
