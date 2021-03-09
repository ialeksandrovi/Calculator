package com.axwel.calculator

interface CalculatorButton {
    fun getTitle(): String
    fun getColorId(): Int
    fun getAction(): Unit
}