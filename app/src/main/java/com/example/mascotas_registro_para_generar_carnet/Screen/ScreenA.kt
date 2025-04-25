package com.example.mascotas_registro_para_generar_carnet.Screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mascotas_registro_para_generar_carnet.R
import androidx.compose.ui.res.painterResource

@Composable
fun ScreenA(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var tamaño by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Color(0xFFF1F3F4))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.log),
            contentDescription = "Logo",
            modifier = Modifier
                .size(140.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Registro de Mascotas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5))


        Spacer(modifier = Modifier.height(24.dp))

        listOf(
            Triple("Nombre", name) { value: String -> name = value },
            Triple("Raza", raza) { value: String -> raza = value },
            Triple("Tamaño", tamaño) { value: String -> tamaño = value },
            Triple("Edad", edad) { value: String -> edad = value },
            Triple("Foto (URL)", foto) { value: String -> foto = value },
        ).forEach { (label, value, onChange) ->
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onChange(it)
                    showError = false
                },
                label = {
                    Row {
                        Text(label, color = Color.DarkGray)
                        Text(" *", color = Color.Red)
                    }
                },
                isError = showError && value.isBlank(),
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = if (label == "Edad") KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
            )
            if (showError && value.isBlank()) {
                Text("Este campo es obligatorio", color = Color.Red, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (name.isBlank() || raza.isBlank() || tamaño.isBlank() || edad.isBlank() || foto.isBlank()) {
                    showError = true
                } else {
                    navController.navigate(
                        "screen_b/${Uri.encode(name)}/${Uri.encode(raza)}/${Uri.encode(tamaño)}/${Uri.encode(edad)}/${Uri.encode(foto)}"
                    )
                }
            },
            modifier = Modifier
                .width(220.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5)),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Generar Carnet", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("screen_b")
            },
            modifier = Modifier
                .width(220.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5)),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text("Ver listas", color = Color.White, fontSize = 18.sp)
        }
    }
}