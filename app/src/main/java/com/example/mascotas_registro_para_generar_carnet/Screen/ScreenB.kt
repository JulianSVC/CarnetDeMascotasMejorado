package com.example.screenejemplo.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mascotas_registro_para_generar_carnet.R
import com.example.navegacion.viewmodel.RegistroViewModel
import android.util.Log
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Height
@Composable
fun ScreenB(
    nombre: String,
    raza: String,
    tamaño: String,
    edad: String,
    foto: String,
    navController: NavController,
    registroViewModel: RegistroViewModel = viewModel()
) {
    // Log para verificar URL
    Log.d("Image URL", foto)

    // Estado mutable para el tamaño
    var currentTamaño by remember { mutableStateOf(tamaño) }

    // Agregar nueva mascota al ViewModel
    LaunchedEffect(nombre, raza, edad) {
        if (nombre.isNotBlank() && raza.isNotBlank() && edad.isNotBlank()) {
            registroViewModel.agregarRegistro(nombre, raza, edad)
        }
    }

    val registros by registroViewModel.registros.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedMascota by remember { mutableStateOf<Triple<String, String, String>?>(null) }

    // Variables para edición
    var editedNombre by remember { mutableStateOf("") }
    var editedRaza by remember { mutableStateOf("") }
    var editedEdad by remember { mutableStateOf("") }
    var editedTamaño by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.log),
                    contentDescription = "Logo",
                    modifier = Modifier.size(120.dp)
                )
            }

            item {
                Text(
                    text = "Mascotas Registradas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E88E5)
                )
            }

            items(registros) { mascota ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Datos de la Mascota", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                            Row {
                                // Botón de editar
                                IconButton(onClick = {
                                    selectedMascota = mascota
                                    editedNombre = mascota.first
                                    editedRaza = mascota.second
                                    editedEdad = mascota.third
                                    editedTamaño = currentTamaño
                                    showEditDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Editar",
                                        tint = Color(0xFF1E88E5)
                                    )
                                }

                                // Botón de eliminar
                                IconButton(onClick = {
                                    selectedMascota = mascota
                                    showDeleteDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Image(
                            painter = rememberAsyncImagePainter(foto),
                            contentDescription = "Foto de la mascota",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(vertical = 8.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        listOf(
                            "Nombre" to mascota.first,
                            "Raza" to mascota.second,
                            "Edad" to mascota.third,
                            "Tamaño" to currentTamaño
                        ).forEach { (label, value) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                when (label) {
                                    "Tamaño" -> Icon(Icons.Default.Height, contentDescription = label)
                                    "Raza" -> Icon(Icons.Default.Pets, contentDescription = label)
                                    else -> Icon(Icons.Default.Info, contentDescription = label)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "$label:",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    value,
                                    modifier = Modifier.weight(2f),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Volver", color = Color.White, fontSize = 18.sp)
                }
            }
        }

        // Diálogo de eliminación
        if (showDeleteDialog && selectedMascota != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Confirmar eliminación") },
                text = { Text("¿Está seguro de eliminar esta mascota?") },
                confirmButton = {
                    TextButton(onClick = {
                        selectedMascota?.let { registroViewModel.eliminarRegistro(it) }
                        showDeleteDialog = false
                        selectedMascota = null
                    }) { Text("Sí") }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false
                        selectedMascota = null
                    }) { Text("No") }
                }
            )
        }

        // Diálogo de edición
        if (showEditDialog && selectedMascota != null) {
            AlertDialog(
                onDismissRequest = { showEditDialog = false },
                title = { Text("Editar mascota") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = editedNombre,
                            onValueChange = { editedNombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editedRaza,
                            onValueChange = { editedRaza = it },
                            label = { Text("Raza") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editedEdad,
                            onValueChange = { editedEdad = it },
                            label = { Text("Edad") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editedTamaño,
                            onValueChange = { editedTamaño = it },
                            label = { Text("Tamaño") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            selectedMascota?.let { original ->
                                registroViewModel.editarRegistro(
                                    original,
                                    editedNombre,
                                    editedRaza,
                                    editedEdad
                                )
                                // Actualizamos el tamaño en el estado
                                currentTamaño = editedTamaño
                            }
                            showEditDialog = false
                        },
                        enabled = editedNombre.isNotBlank() &&
                                editedRaza.isNotBlank() &&
                                editedEdad.isNotBlank() &&
                                editedTamaño.isNotBlank()
                    ) {
                        Text("Guardar cambios")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEditDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}