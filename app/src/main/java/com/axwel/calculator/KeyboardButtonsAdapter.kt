package com.axwel.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axwel.calculator.databinding.DefaultCustomButtonBinding

class KeyboardButtonsAdapter(private val context: Context?): RecyclerView.Adapter<KeyBoardViewHolder>() {
    private var fields: MutableList<KeyboardButton> = mutableListOf()
    lateinit var operationListener: OperationListener

    fun setFields(fields: MutableList<KeyboardButton>) {
        this.fields = fields
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyBoardViewHolder {

        val itemButtonBinding = DefaultCustomButtonBinding.inflate(LayoutInflater.from(context?.applicationContext), parent, false)
        return KeyBoardViewHolder(itemButtonBinding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: KeyBoardViewHolder, position: Int) {
        val item = fields[position]
        holder.btn.text = item.name
        holder.btn.background = context?.resources?.getDrawable(item.getStyle())
        holder.btn.setOnClickListener {
            //TODO operationListener.setOperation(item.operation)
        }
    }

    override fun getItemCount(): Int {
        return fields.size
    }
}

interface OperationListener{
    fun setOperation(operation: Operation)
}

class KeyBoardViewHolder(itemButtonBinding: DefaultCustomButtonBinding) : RecyclerView.ViewHolder(itemButtonBinding.root) {
    var btn: Button = itemButtonBinding.btnButton
}