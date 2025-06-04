package com.example.loginactivityshared

sealed class Route(val route: String) {
    object SignUp : Route("sign_up")
    object SignIn : Route("sign_in")
    object Home : Route("home")
    object MainPage : Route("main_page")  // Fixed naming convention

    companion object {
        fun fromRoute(route: String?): Route = when (route) {
            SignUp.route -> SignUp
            SignIn.route -> SignIn
            Home.route -> Home
            MainPage.route -> MainPage
            null -> Home // Default to home
            else -> throw IllegalArgumentException("Unknown route: $route")
        }
    }
}