package com.example.retobancopichincha.utils.state

data class ButtonState(
    val text: String,
    val onButtonClicked: () -> Unit
)