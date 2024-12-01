package com.example.retobancopichincha.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(
    selection: Int = 1,
    onSelectScreen: (String) -> Unit = {},
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val topName = listOf("Recetas", "Detalles","Favoritos")
    var topAppName by remember { mutableStateOf("Recetas") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = drawerContent,
        drawerState = drawerState,
        content = {
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                topBar = {
                    TopAppBar(
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "MENU",
                                        tint = Color(0xFFD4AF37),
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = topName[selection],
                                    fontSize = 20.sp,
                                    color = Color(0xFFD4AF37),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },
                        actions = {},
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color(0xFF000000)
                        )
                    )
                },
                bottomBar = {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFF000000))
                        ) {
                            BottomNavigation(
                                items = listOf(
                                    BottomNavigationItem(
                                        icon = Icons.Default.Home,
                                        text = "Recetas"
                                    ),
                                    BottomNavigationItem(
                                        icon = Icons.Default.List,
                                        text = "Detalles"
                                    ),
                                    BottomNavigationItem(
                                        icon = Icons.Default.List,
                                        text = "Favoritos"
                                    )
                                ),
                                selectedItem = selection,
                                onItemClick = { index ->
                                    when (index) {
                                        0 -> {
                                            onSelectScreen("home")
                                            topAppName = "Recetas"
                                        }

                                        1 -> {
                                            onSelectScreen("details")
                                            topAppName = "Detalles"
                                        }

                                        2 -> {
                                            onSelectScreen("favorite")
                                            topAppName = "Favoritos"
                                        }
                                    }
                                },
                            )
                        }
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
                content = { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        content()
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewScaffoldInventory() {
    Scaffold(
        drawerContent = {
            AppDrawer(
                modifier = Modifier,
                onNavigation = {},
                onMenuItemClick = {}
            )
        }) {
        Column {

        }
    }
}