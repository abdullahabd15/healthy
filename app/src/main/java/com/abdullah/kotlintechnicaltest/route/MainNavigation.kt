package com.abdullah.kotlintechnicaltest.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdullah.kotlintechnicaltest.auth.screens.login.LoginScreen
import com.abdullah.kotlintechnicaltest.auth.screens.register.RegisterScreen
import com.abdullah.kotlintechnicaltest.main.screens.main.MainScreen
import com.abdullah.kotlintechnicaltest.main.screens.sidemenu.SideMenuScreen
import com.abdullah.kotlintechnicaltest.profile.screens.ProfileScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = AppRoute.Login.routeName) {
        composable(route = AppRoute.Main.routeName) {
            MainScreen(
                onMenuClick = {
                    navController.navigate(AppRoute.SideMenu.routeName)
                }
            )
        }
        composable(route = AppRoute.Login.routeName) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoute.Main.routeName) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }, onRegisterClick = {
                    navController.navigate(AppRoute.Register.routeName)
                })
        }
        composable(route = AppRoute.Register.routeName) {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(AppRoute.Login.routeName)
                },
                onRegisterSuccess = {
                    navController.navigate(AppRoute.Login.routeName) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = AppRoute.SideMenu.routeName) {
            SideMenuScreen(
                onCloseClick = { navController.navigateUp() },
                onLogoutSuccess = {
                    navController.navigate(AppRoute.Login.routeName) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onMyProfileClick = {
                    navController.popBackStack()
                    navController.navigate(AppRoute.Profile.routeName)
                })
        }
        composable(route = AppRoute.Profile.routeName) {
            ProfileScreen(
                onMenuClick = {
                    navController.navigate(AppRoute.SideMenu.routeName)
                }
            )
        }
    }
}