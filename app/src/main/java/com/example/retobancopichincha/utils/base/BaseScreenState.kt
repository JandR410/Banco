package com.example.retobancopichincha.utils.base

import com.example.retobancopichincha.utils.state.DialogState
import com.example.retobancopichincha.utils.state.NoInternetDialogState

data class BaseScreenState<T>(
    val state: T,
    val loading: Boolean = false,
    val dialog: DialogState? = null,
    val noInternetDialog: NoInternetDialogState? = null
)