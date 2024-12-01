package com.example.retobancopichincha.utils.state

import com.example.retobancopichincha.utils.base.BaseScreenState
import com.example.retobancopichincha.utils.base.ScreenState
import kotlinx.coroutines.flow.StateFlow

interface StateHandler<S : ScreenState> {
    val screenState: StateFlow<BaseScreenState<S>>
    val state: S

    fun setState(newState: S)

    fun showLoading()
    fun hideLoading()

    fun showDialog(dialog: DialogState)
    fun showGenericDialog(dialog: DialogState, onButtonClicked: () -> Unit)
    fun dismissDialog()

    fun showNoInternetScreen(onButtonRetryClicked: () -> Unit)
}