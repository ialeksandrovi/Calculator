package com.axwel.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KeyboardButtonsAdapter(private val context: Context?): RecyclerView.Adapter<ViewHolder>() {
    private var mFields: MutableList<DefaultCustomButton> = mutableListOf()

    fun setFields(fields: MutableList<DefaultCustomButton>) {
        mFields = fields
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.default_custom_button, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mFields[position]
        holder.title.text = item.getTitle()
        holder.title.setTextColor(item.getColorId())
        holder.title.setOnClickListener {
            item.getAction()
        }
    }

    override fun getItemCount(): Int {
        return mFields.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById<View>(R.id.text) as TextView
}