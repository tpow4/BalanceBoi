package com.balance.boi.ui

sealed class Screen(val route: String) {
    object Overview : Screen("overview")
    object AddAccount : Screen("add_account")
    object Settings: Screen("settings")
}
