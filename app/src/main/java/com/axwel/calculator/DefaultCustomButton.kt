package com.axwel.calculator

class DefaultCustomButton(
        override val name: String,
        override val operation: Operation): KeyboardButton {
        override fun getStyle(): Int {
                return R.drawable.bordered_button
        }
}