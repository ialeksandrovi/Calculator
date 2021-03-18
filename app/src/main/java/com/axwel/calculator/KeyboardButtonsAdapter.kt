package com.axwel.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.axwel.calculator.databinding.DefaultCustomButtonBinding

class KeyboardButtonsAdapter(
        private val context: Context,
        val listener: OperationListener
        ): RecyclerView.Adapter<KeyBoardViewHolder>() {
    private var fields: MutableList<KeyboardButton> = mutableListOf()

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
        with(holder.btn) {
            text = item.name
            background = context.resources?.getDrawable(item.getStyle())
            setOnClickListener {
                listener.keyPicked(KeyBoardButtonModel(item))
            }
        }
    }

    override fun getItemCount(): Int {
        return fields.size
    }
}

interface OperationListener{
    fun keyPicked(buttonModel: KeyBoardButtonModel)
}

class KeyBoardViewHolder(itemButtonBinding: DefaultCustomButtonBinding) : RecyclerView.ViewHolder(itemButtonBinding.root) {
    var btn: Button = itemButtonBinding.btnButton
}