package com.axwel.calculator

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable


class DefaultCustomButton(
        override val name: String,
        override val operation: Operation): KeyboardButton {
        override fun getStyle(): Int {
                return R.drawable.bordered_button
        }
}