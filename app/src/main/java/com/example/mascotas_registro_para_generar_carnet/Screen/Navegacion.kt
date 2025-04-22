package com.example.mascotas_registro_para_generar_carnet.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController)
        }
        composable(
            "screen_b/{name}/{raza}/{tamaño}/{edad}/{foto}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("raza") { type = NavType.StringType },
                navArgument("tamaño") { type = NavType.StringType },
                navArgument("edad") { type = NavType.StringType },
                navArgument("foto") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val raza = backStackEntry.arguments?.getString("raza") ?: ""
            val tamaño = backStackEntry.arguments?.getString("tamaño") ?: ""
            val edad = backStackEntry.arguments?.getString("edad") ?: ""
            val foto = backStackEntry.arguments?.getString("foto") ?: ""

            ScreenB(name, raza, tamaño, edad, foto)
        }
    }
}

