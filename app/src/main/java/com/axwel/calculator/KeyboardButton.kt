package com.axwel.calculator

import android.graphics.drawable.Drawable

interface KeyboardButton {
    val name: String
    val operation: Operation
    fun getStyle(): Int
}