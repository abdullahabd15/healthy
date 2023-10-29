package com.abdullah.kotlintechnicaltest.route

sealed class AppRoute(val routeName: String) {
    object Login : AppRoute("/login")
    object Register : AppRoute("/register")
    object Main : AppRoute("/main")
    object SideMenu : AppRoute("/side-menu")
    object Profile : AppRoute("/profile")
}
