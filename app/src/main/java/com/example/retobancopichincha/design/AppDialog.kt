package com.example.retobancopichincha.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String = "",
    firstButtonText: String? = null,
    onFirstButtonClicked: () -> Unit = {},
    secondaryButtonText: String? = null,
    onSecondaryButtonClicked: () -> Unit = {},
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = modifier
                .fillMaxHeight()
                .padding(horizontal = 24.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = message,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
                firstButtonText?.let { buttonText ->
                    AppButton(
                        onClick = onFirstButtonClicked,
                        modifier = Modifier.padding(top = 24.dp)
                    ) {
                        Text(
                            text = buttonText,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                secondaryButtonText?.let { buttonText ->
                    TextButton(onClick = onSecondaryButtonClicked) {
                        Text(
                            text = buttonText,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppDialogPreview() {
    AppDialog(
        title = "Dialog",
        message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        firstButtonText = "Entendido",
        secondaryButtonText = "Ahora no"
    )
}

@Preview
@Composable
fun DefaultErrorDialog() {
    AppDialog(
        title = "Parece que algo salió mal",
        message = "Lo sentimos, por favor vuelve a intentar",
        firstButtonText = "Entendido"
    )
}
