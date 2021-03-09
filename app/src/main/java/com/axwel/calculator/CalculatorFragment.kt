package com.axwel.calculator



import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axwel.calculator.presenter.CalculatorPresenter

class CalculatorFragment: Fragment() {
    private val adapter = KeyboardButtonsAdapter(context)
    private val presenter = CalculatorPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvList)
        recyclerView.layoutManager = GridLayoutManager(context, 4)

        recyclerView.adapter = adapter
        adapter.setFields(generatePseudoButtons())
    }

    private fun generatePseudoButtons(): MutableList<DefaultCustomButton> {
        val list = mutableListOf<DefaultCustomButton>()
        list.add(DefaultCustomButton("1", R.color.purple_700, presenter.action()))
        list.add(DefaultCustomButton("2", R.color.purple_500, presenter.action()))
        list.add(DefaultCustomButton("4", R.color.purple_200, presenter.action()))
        list.add(DefaultCustomButton("3", R.color.purple_700, presenter.action()))
        list.add(DefaultCustomButton("5", R.color.purple_500, presenter.action()))
        list.add(DefaultCustomButton("6", R.color.purple_200, presenter.action()))
        list.add(DefaultCustomButton("7", R.color.purple_700, presenter.action()))
        return list
    }


    companion object {
        fun newInstance(): CalculatorFragment {
            return CalculatorFragment()
        }
    }
}