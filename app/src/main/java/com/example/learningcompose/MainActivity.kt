package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learningcompose.ui.provider.StringProvider
import com.example.learningcompose.ui.screen.Route
import com.example.learningcompose.ui.screen.explore.ExploreScreen
import com.example.learningcompose.ui.screen.home.HomeScreen
import com.example.learningcompose.ui.screen.orders.OrderScreen
import com.example.learningcompose.ui.screen.profile.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up fullscreen and transparent status bar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        // Optionally, set light or dark status bar icons
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val topLevelRoutes = listOf(
        TopLevelRoute(
            name = StringProvider.home,
            route =Route.Home.route,
            icon = ImageVector.vectorResource(R.drawable.ic_home),
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_selected_home)
        ),
        TopLevelRoute(
            name = StringProvider.explore,
            route = Route.Explore.route,
            icon = ImageVector.vectorResource(R.drawable.ic_explore),
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_selected_explore)
        ),
        TopLevelRoute(
            name = StringProvider.orders,
            route = Route.Orders.route,
            icon = ImageVector.vectorResource(R.drawable.ic_order),
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_selected_orders)
        ),
        TopLevelRoute(
            name = StringProvider.profile,
            route = Route.Profile.route,
            icon = ImageVector.vectorResource(R.drawable.ic_profile),
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_selected_profile)
        ),
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                modifier = Modifier.padding(bottom = 10.dp),
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                topLevelRoutes.forEach { topLevelRoute ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                if (currentDestination?.route == topLevelRoute.route) topLevelRoute.selectedIcon else topLevelRoute.icon,
                                contentDescription = topLevelRoute.name,
                                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                                tint = if (currentDestination?.route == topLevelRoute.route) Color.Red else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                topLevelRoute.name,
                                Modifier.padding(bottom = 10.dp),
                                color = if (currentDestination?.route == topLevelRoute.route) Color.Red else Color.Gray // Set color dynamically
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(navController, startDestination = "Home", Modifier.padding(innerPadding)) {
            composable(Route.Home.route) { HomeScreen() }
            composable(Route.Explore.route) { ExploreScreen() }
            composable(Route.Orders.route) { OrderScreen() }
            composable(Route.Profile.route) { ProfileScreen() }
        }
    }
}

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector, val selectedIcon: ImageVector)

