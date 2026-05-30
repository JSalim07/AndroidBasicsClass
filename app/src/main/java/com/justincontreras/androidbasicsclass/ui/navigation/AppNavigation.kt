package com.justincontreras.androidbasicsclass.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.justincontreras.androidbasicsclass.ui.login.view.LoginView
import com.justincontreras.androidbasicsclass.ui.thirdpartialids2.firstApiRequest.view.FirstApiRequestView
import com.justincontreras.androidbasicsclass.ui.thirdpartialids2.homeThirdPartialIDS2.view.HomeThirdPartialIDS2View

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val rootNavController = rememberNavController()

    NavHost(navController = rootNavController, startDestination = AppRoute.Login.route) {
        composable(AppRoute.Login.route) {
            LoginView(
                onLoginSuccess = {
                    rootNavController.navigate("tabs") {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable("tabs") {
            TabsScaffold(
                onNavigateToApi = { rootNavController.navigate(AppRoute.FirstApiRequest.route) }
            )
        }
        composable(AppRoute.FirstApiRequest.route) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Expedientes Académicos", fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = { rootNavController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Regresar"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    FirstApiRequestView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabsScaffold(onNavigateToApi: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentTab = AppRoute.routes.find { it.route == currentRoute } ?: AppRoute.ThirdPartialIDS2

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        text = currentTab.label.uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        },
        bottomBar = {
            NavigationBar(
                tonalElevation = 8.dp
            ) {
                AppRoute.routes.forEach { tab ->
                    val isSelected = currentRoute == tab.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (currentRoute != tab.route) {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { 
                            Icon(
                                imageVector = tab.icon, 
                                contentDescription = tab.label
                            ) 
                        },
                        label = { 
                            Text(
                                text = tab.label,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ) 
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.ThirdPartialIDS2.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoute.ThirdPartialIDS2.route) {
                HomeThirdPartialIDS2View(onNavigateToApi = onNavigateToApi)
            }
            composable(AppRoute.FirstApiRequest.route) {
                FirstApiRequestView()
            }
            composable(AppRoute.PersonalInfo.route) {
                PlaceholderView(AppRoute.PersonalInfo)
            }
            composable(AppRoute.Settings.route) {
                PlaceholderView(AppRoute.Settings)
            }
            composable(AppRoute.Page3.route) {
                PlaceholderView(AppRoute.Page3)
            }
        }
    }
}

@Composable
fun PlaceholderView(route: AppRoute) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier
                    .padding(40.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = route.icon,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = route.label,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "Este módulo se encuentra bajo desarrollo profesional.\nPróximamente estará disponible.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            }
        }
    }
}
