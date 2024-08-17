package com.balance.boi.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.balance.boi.viewmodels.BiometricAuthState
import com.balance.boi.viewmodels.BiometricsViewModel
import com.balance.boi.ui.Screen

@Composable
fun BiometricScreen(
    navController: NavController,
    biometricsViewModel: BiometricsViewModel
) {
    val context = LocalContext.current
    val authState by biometricsViewModel.biometricAuthState.collectAsState()

    LaunchedEffect(Unit) {
        biometricsViewModel.authenticate(context)
    }

    when (authState) {
        is BiometricAuthState.Idle -> {
            // Show idle state
        }
        is BiometricAuthState.Success -> {
            Text("Authentication Successful")
            navController.navigate(Screen.Overview.route)
        }
        is BiometricAuthState.Failed -> {
            // Show failed state
            Text("Authentication Failed")
        }
        is BiometricAuthState.Error -> {
            // Show error message
            val errorMessage = (authState as BiometricAuthState.Error).message
            Text("Authentication Error: $errorMessage")
        }
    }
}