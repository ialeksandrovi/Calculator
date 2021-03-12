package com.axwel.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axwel.calculator.databinding.DefaultCustomButtonBinding

class KeyboardButtonsAdapter(private val context: Context?): RecyclerView.Adapter<KeyBoardViewHolder>() {
    private var fields: MutableList<DefaultCustomButton> = mutableListOf()

    fun setFields(fields: MutableList<DefaultCustomButton>) {
        this.fields = fields
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyBoardViewHolder {
        val itemButtonBinding = DefaultCustomButtonBinding.inflate(LayoutInflater.from(context), parent, false)
        return KeyBoardViewHolder(itemButtonBinding)
    }

    override fun onBindViewHolder(holder: KeyBoardViewHolder, position: Int) {
        val item = fields[position]
        holder.title.text = item.getTitle()
        holder.title.setTextColor(item.getColorId())
        holder.title.setOnClickListener {
            item.getAction()
        }
    }

    override fun getItemCount(): Int {
        return fields.size
    }
}

class KeyBoardViewHolder(itemButtonBinding: DefaultCustomButtonBinding) : RecyclerView.ViewHolder(itemButtonBinding.root) {
    var title: TextView = itemView.findViewById<View>(R.id.text) as TextView
}