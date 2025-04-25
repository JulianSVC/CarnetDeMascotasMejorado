package com.example.mascotas_registro_para_generar_carnet.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegacion.viewmodel.RegistroViewModel
import com.example.screenejemplo.Screen.ScreenB
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController)
        }
        composable(
            "screen_b/{nombre}/{raza}/{tamaño}/{edad}/{foto}",  // Quitamos correo y añadimos tamaño
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("raza") { type = NavType.StringType },
                navArgument("tamaño") { type = NavType.StringType },  // Nuevo parámetro
                navArgument("edad") { type = NavType.StringType },
                navArgument("foto") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val raza = backStackEntry.arguments?.getString("raza") ?: ""
            val tamaño = backStackEntry.arguments?.getString("tamaño") ?: ""  // Obtenemos tamaño
            val edad = backStackEntry.arguments?.getString("edad") ?: ""
            val foto = backStackEntry.arguments?.getString("foto") ?: ""

            val registroViewModel: RegistroViewModel = viewModel()
            ScreenB(
                nombre = nombre,
                raza = raza,
                tamaño = tamaño,  // Pasamos tamaño en lugar de correo
                edad = edad,
                foto = foto,
                navController = navController,
                registroViewModel = registroViewModel
            )
        }
    }
}