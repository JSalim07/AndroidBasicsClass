package com.justincontreras.androidbasicsclass.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppRoute(val route: String, val label: String, val icon: ImageVector) {
    object Login : AppRoute("login", "Acceso", Icons.Filled.Lock)
    object ThirdPartialIDS2 : AppRoute("third_partial_ids2", "Inicio", Icons.Filled.Home)
    object FirstApiRequest : AppRoute("api_request", "Alumnos", Icons.AutoMirrored.Filled.List)
    object PersonalInfo : AppRoute("personal_info", "Perfil", Icons.Filled.Badge)
    object Settings : AppRoute("settings", "Ajustes", Icons.Filled.Settings)
    object Page3 : AppRoute("page_3", "Módulos", Icons.Filled.Layers)

    companion object {
        val routes = listOf(ThirdPartialIDS2, FirstApiRequest, PersonalInfo, Settings, Page3)
    }
}
