package com.axwel.calculator

import android.annotation.SuppressLint

class OperationCustomButton(
        override val name: String,
        override val operation: Operation) : KeyboardButton {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getStyle(): Int {
        return R.drawable.custom_button_border
    }
}