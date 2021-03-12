package com.axwel.calculator

import android.content.Context
import androidx.appcompat.widget.AppCompatButton

class DefaultCustomButton(
        val titleButton: String,
        val colorIdButton: Int,
        val actionButton: Unit
): CalculatorButton {
        override fun getTitle(): String {
                return titleButton
        }

        override fun getColorId(): Int {
                return colorIdButton
        }

        override fun getAction() {
                return actionButton
        }

}