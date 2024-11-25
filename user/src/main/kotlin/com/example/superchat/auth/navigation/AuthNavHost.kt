package com.example.superchat.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.superchat.auth.AuthState
import com.example.superchat.auth.signIn.SignInRoute
import com.example.superchat.auth.signIn.signInScreen
import com.example.superchat.auth.signup.navigateToSignup
import com.example.superchat.auth.signup.signupScreen

@Composable
fun AuthNavHost(
    authState: AuthState,
    modifier: Modifier = Modifier
){
    val navController = authState.navController

    NavHost(
        navController = navController,
        startDestination = SignInRoute,
        modifier = modifier,
    ) {
        signInScreen(onSignupClick = navController::navigateToSignup)

        signupScreen(
            onBackClick = navController::popBackStack,
            onSignUp = {
                navController.popBackStack(SignInRoute, false)
            }
        )
    }

}