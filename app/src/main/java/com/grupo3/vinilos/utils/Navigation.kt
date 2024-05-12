package com.grupo3.vinilos.utils

import androidx.navigation.NavController


fun navigateToWithState(route:String, navController: NavController): Unit{
    navController.navigate(route){
        popUpTo(navController.graph.startDestinationId) {
            saveState = false
        }
        launchSingleTop = true
        restoreState = true
    }
}