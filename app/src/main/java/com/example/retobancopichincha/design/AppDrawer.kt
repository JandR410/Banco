package com.example.retobancopichincha.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppDrawer(
    onMenuItemClick: (String) -> Unit,
    onNavigation: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxHeight()
            .fillMaxWidth(2f / 3f)
            .background(Color(0xFF000000))
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color(0xFFF3F0F0), shape = RoundedCornerShape(24.dp))
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                )
            }


            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Bienvenido",
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD4AF37),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color(0xFFD4AF37))
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(label = "Recetas", onClick = { onNavigation("home") })
        DrawerMenuItem(label = "Detalles", onClick = { onNavigation("details") })
        DrawerMenuItem(label = "Favoritos", onClick = { onNavigation("favorite") })
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(color = Color(0xFFD4AF37))
        DrawerMenuItem(
            label = "Cerrar sesion",
            onClick = { onMenuItemClick("/login") },
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun DrawerMenuItem(
    label: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 18.sp
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = fontSize,
            color = Color(0xFFD4AF37)
        )
    }
}