package com.axwel.calculator

interface KeyboardButton {
    val name: String
    val operation: Operation
    fun getStyle(): Int
}