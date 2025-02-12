package com.example.authaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authaula.data.AuthRepository
import com.example.authaula.ui.theme.AuthAulaTheme
import com.example.authaula.ui.view.ForgotPasswordScreen
import com.example.authaula.ui.view.HomeScreen
import com.example.authaula.ui.view.LoginScreen
import com.example.authaula.ui.view.RegisterScreen
import com.example.authaula.viewmodel.AuthViewModel
import com.example.authaula.viewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = AuthRepository()
        val authViewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(
            AuthViewModel::class.java)

        setContent {
            AuthAulaTheme {
                val navController: NavHostController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(authViewModel, navController) }
                    composable("register") { RegisterScreen(authViewModel, navController) }
                    composable("forgotPassword") {
                        ForgotPasswordScreen(
                            authViewModel,
                            navController
                        )
                    }
                    composable("home") { HomeScreen(authViewModel, navController) }
                }
            }
        }
    }
}
