package com.example.retobancopichincha.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFF000000),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(22.dp),
                            tint = if (index == selectedItem) Color(0xFFD4AF37) else Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelMedium,
                            color = if (index == selectedItem) Color(0xFFD4AF37) else Color.White
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFD4AF37),
                    selectedTextColor = Color(0xFFD4AF37),
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color(0xFF444444)
                ),
            )
        }
    }
}

@Preview
@Composable
fun CatsBottomNavigationPreview() {
    BottomNavigation(items = listOf(
        BottomNavigationItem(icon = Icons.Default.Home, text = "General"),
        BottomNavigationItem(icon = Icons.Default.Home, text = "Internet"),
        BottomNavigationItem(icon = Icons.Default.Home, text = "Pendiente"),
    ), selectedItem = 0, onItemClick = {})
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String
)