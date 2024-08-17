package com.balance.boi.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balance.boi.viewmodels.AccountViewModel
import com.balance.boi.viewmodels.BiometricsViewModel
import com.balance.boi.ui.screens.BiometricScreen
import com.balance.boi.ui.screens.SettingsScreen
import com.balance.boi.ui.screens.NewAccountScreen
import com.balance.boi.ui.screens.OverviewScreen

@Composable
fun BalanceBoiApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Biometrics.route
    ) {
        composable(Screen.Overview.route) {
            val viewModel: AccountViewModel = hiltViewModel()
            OverviewScreen(navController, viewModel)
        }
        composable(Screen.AddAccount.route) {
            val viewModel: AccountViewModel = hiltViewModel()
            NewAccountScreen(navController, viewModel)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }

        composable(Screen.Biometrics.route) {
            val viewModel: BiometricsViewModel = hiltViewModel()
            BiometricScreen(navController, viewModel)
        }
    }
}