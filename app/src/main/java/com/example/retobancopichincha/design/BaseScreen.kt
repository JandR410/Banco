package com.example.retobancopichincha.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.retobancopichincha.utils.base.BaseScreenState
import com.example.retobancopichincha.utils.state.ButtonState
import com.example.retobancopichincha.utils.state.DialogState
import com.example.retobancopichincha.utils.state.NoInternetDialogState

@Composable
fun BaseScreen(
    baseScreenState: BaseScreenState<*>,
    content: @Composable () -> Unit
) {
    BaseScreen(
        loading = baseScreenState.loading,
        dialog = baseScreenState.dialog,
        noInternetDialog = baseScreenState.noInternetDialog,
        content = content
    )
}

@Composable
private fun BaseScreen(
    loading: Boolean = false,
    dialog: DialogState? = null,
    noInternetDialog: NoInternetDialogState? = null,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.White)
    ) {
        content()

        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        dialog?.let {
            AppDialog(
                title = dialog.title,
                message = dialog.message,
                firstButtonText = dialog.firstButton?.text,
                onFirstButtonClicked = dialog.firstButton?.onButtonClicked ?: {},
                secondaryButtonText = dialog.secondButton?.text,
                onSecondaryButtonClicked = dialog.secondButton?.onButtonClicked ?: {}
            )
        }
        noInternetDialog?.let {
            NoInternetScreen(onButtonRetryClicked = noInternetDialog.onButtonRetryClicked)
        }
    }
}

@Preview
@Composable
fun BaseScreenWithLoadingPreview() {
    BaseScreen(loading = true) {
        Text("BaseScreen con indicador de carga")
    }
}

@Preview
@Composable
fun BaseScreenWithDialogPreview() {
    BaseScreen(
        loading = true,
        dialog = DialogState(
            title = "Parece que algo salió mal",
            message = "Lo sentimos, por favor vuelve a intentar",
            firstButton = ButtonState(text = "Entendido") {},
            secondButton = null
        )
    ) {
        Text(text = "BaseScreen con diálogo genérico cuando algo salió mal")
    }
}

@Preview
@Composable
fun BaseScreenWithNoInternetScreenPreview() {
    BaseScreen(
        loading = true,
        noInternetDialog = NoInternetDialogState(
            onButtonRetryClicked = {}
        )
    ) {
        Text(text = "BaseScreen cuando no hay conexión a internet")
    }
}