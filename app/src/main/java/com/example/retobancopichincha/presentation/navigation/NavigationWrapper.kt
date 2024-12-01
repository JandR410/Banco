package com.example.retobancopichincha.presentation.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onboarding.ui.onboarding.OnboardingViewModel
import com.example.retobancopichincha.design.AppDrawer
import com.example.retobancopichincha.design.BaseScreen
import com.example.retobancopichincha.design.Scaffold
import com.example.retobancopichincha.presentation.details.DetailScreen
import com.example.retobancopichincha.presentation.favorite.FavoriteScreen
import com.example.retobancopichincha.presentation.home.HomeScreen
import com.example.retobancopichincha.presentation.home.HomeViewModel
import com.example.retobancopichincha.presentation.onboarding.OnboardingScreen
import com.example.retobancopichincha.presentation.splash.SplashScreen
import com.example.retobancopichincha.presentation.splash.SplashViewModel

@Composable
fun NavigationWrapper() {

    val darkTheme: Boolean = isSystemInDarkTheme()

    val isDarkTheme = remember { mutableStateOf(darkTheme) }

    val navController = rememberNavController()

    MaterialTheme(
        colorScheme = if (isDarkTheme.value) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {

        NavHost(navController = navController, startDestination = "/login") {

            composable("/login") {
                val viewModel: SplashViewModel = hiltViewModel()
                val screenState = viewModel.screenState.collectAsState().value
                BaseScreen(baseScreenState = screenState) {
                    SplashScreen(
                        screenActionHandler = viewModel::handleScreenActions,
                        events = viewModel.eventsFlow,
                        navigation = {
                            navController.navigate(it)
                        }
                    )
                }
            }

            composable("/onboarding") {
                val viewModel: OnboardingViewModel = hiltViewModel()
                val screenState = viewModel.screenState.collectAsState().value
                BaseScreen(baseScreenState = screenState) {
                    OnboardingScreen(
                        screenActionHandler = viewModel::handleScreenActions,
                        events = viewModel.eventsFlow,
                        navigateToInit = {
                            navController.navigate(it)
                        }
                    )
                }

            }

            composable("/home") {
                val viewModel: HomeViewModel = hiltViewModel()
                val screenState = viewModel.screenState.collectAsState().value
                val navigation = remember { mutableStateOf("home") }
                var currentScreen by remember { mutableIntStateOf(0) }

                Scaffold(
                    selection = currentScreen,
                    onSelectScreen = { screenName ->
                        when (screenName) {
                            "home" -> {
                                currentScreen = 0
                                navigation.value = "home"
                            }

                            "details" -> {
                                currentScreen = 1
                                navigation.value = "details"
                            }

                            "favorite" -> {
                                currentScreen = 2
                                navigation.value = "favorite"
                            }


                        }
                    },
                    drawerContent = {
                        AppDrawer(
                            modifier = Modifier,
                            onNavigation = { navigation.value = it },
                            onMenuItemClick = {
                                navController.navigate(it)
                            }
                        )
                    }
                ) {
                    when (navigation.value) {
                        "home" -> {
                            BaseScreen(baseScreenState = screenState) {
                                HomeScreen(
                                    screenActionHandler = viewModel::handleScreenActions,
                                    events = viewModel.eventsFlow,
                                    navigate = {
                                        currentScreen = 1
                                        navigation.value = "details"
                                    },
                                    state = screenState.state
                                )
                            }

                        }

                        "details" -> {
                            BaseScreen(baseScreenState = screenState) {
                                DetailScreen(
                                    screenActionHandler = viewModel::handleScreenActions,
                                    state = screenState.state
                                )
                            }
                        }

                        "favorite" -> {
                            BaseScreen(baseScreenState = screenState) {
                                FavoriteScreen(
                                    screenActionHandler = viewModel::handleScreenActions,
                                    events = viewModel.eventsFlow,
                                    state = screenState.state,
                                    navigate = {
                                        currentScreen = 1
                                        navigation.value = "details"
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

