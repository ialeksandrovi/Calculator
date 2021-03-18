package com.axwel.calculator.presenter

import android.content.Context

const val PLUS = "+"
const val MINUS = "-"
const val MULTIPLICATION = "*"
const val DIVISION = "/"

class CalculatorPresenter(
        private val context: Context,
        private val listener: PresenterListener
) {
   private var monitorText: String = ""
   private var total: Double = 0.0
   private var firstOperator: Double = 0.0
   private var secondOperator: Double = 0.0
   private lateinit var operation: String
   private var memory: Double = 0.0


   fun addNumber(number: Int) {
      monitorText += number
      listener.monitorStateChanged(monitorText)
   }

   fun addPoint() {
      when {
          monitorText.isEmpty() -> {
             monitorText += "0."
          }
          monitorText.last() == '.' -> {
             monitorText = monitorText
          }
          monitorText.substringAfterLast(' ').contains('.') -> monitorText = monitorText
          monitorText.last() == ' '-> {
             monitorText += "0."
          }
          else -> {
             monitorText += "."
          }
      }
      listener.monitorStateChanged(monitorText)
   }

   fun addition(){
      validateBinaryOperands()
      validate()
      monitorText += " + "
      listener.monitorStateChanged(monitorText)
   }

   fun subtraction(){
      validateBinaryOperands()
      validate()
      monitorText += " - "
      listener.monitorStateChanged(monitorText)
   }

   fun multiplication(){
      validateBinaryOperands()
      validate()
      monitorText += " * "
      listener.monitorStateChanged(monitorText)
   }

   fun division(){
      validateBinaryOperands()
      validate()
      monitorText += " / "
      listener.monitorStateChanged(monitorText)
   }

   private fun validate(){
      if (monitorText.last() == '.') {
         monitorText += "0"
      }
   }

   private fun validateBinaryOperands() {
      if (monitorText.filter { it == ' ' }.length >= 2) {
         getTotal()
      }
   }

   private fun validateOperandsBeforeTotal() {
      if (monitorText.isEmpty()) {
         monitorText = "0 + 0"
      }
      if (monitorText.isNotEmpty() && !monitorText.contains(' ')) {
         monitorText += " + 0"
      }
      if (monitorText.endsWith(" + ") || monitorText.endsWith(" - ")) {
         monitorText += "0"
      }
      if (monitorText.endsWith(" * ") || monitorText.endsWith(" / ")){
         monitorText += "1"
      }
   }

   fun getTotal(){
      validateOperandsBeforeTotal()
      validate()
      firstOperator = monitorText.substring(0, monitorText.indexOf(" ")).toDouble()
      val index = monitorText.indexOf(" ")
      operation = monitorText.substring(index + 1, index + 2)
      secondOperator = monitorText.substring(index + 2).toDouble()
      processGetTotal()
      monitorText = total.toString()
      listener.monitorStateChanged(monitorText)
      total = 0.0
   }

   private fun processGetTotal() {
      total = when (operation) {
         PLUS -> firstOperator.plus(secondOperator)
         MINUS -> firstOperator.minus(secondOperator)
         MULTIPLICATION -> firstOperator.times(secondOperator)
         DIVISION -> {
             if (secondOperator == 0.0) {
                0.0
             } else {
                firstOperator.div(secondOperator)
             }
         }
         else -> total
      }
   }

   fun saveInMemory() {
      memory = if (total == 0.0 && monitorText.none { monitorText.contains(" ") }) {
         monitorText.toDouble()
      } else {
         total
      }
   }

   fun getFromMemory() {
      if (monitorText.isNotEmpty() && monitorText.last() != ' ') {
         monitorText = memory.toString()
      } else {
         monitorText += memory.toString()
      }
      listener.monitorStateChanged(monitorText)
   }

   fun clearMemory() {
      memory = 0.0
   }

   fun clear() {
      monitorText = ""
      listener.monitorStateChanged("0.0")
   }
}

interface PresenterListener {
   fun monitorStateChanged(textMonitor: String)
}