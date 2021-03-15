package com.axwel.calculator



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axwel.calculator.databinding.FragmentCalculatorBinding
import com.axwel.calculator.presenter.CalculatorPresenter

class CalculatorFragment: Fragment() {
    lateinit var keyboardAdapter: KeyboardButtonsAdapter
    private val presenter = context?.let { CalculatorPresenter(it) }
    private var viewBinding: FragmentCalculatorBinding? = null
    private var recyclerView: RecyclerView? = null
    private var monitor: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentCalculatorBinding.inflate(inflater, container, false)

        return viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyboardAdapter = KeyboardButtonsAdapter(context, object : OperationListener {
            override fun keyPicked(model: KeyBoardButtonModel) {
                if (model.keyboardButton is OperationCustomButton) {
                    when (model.keyboardButton.operation) {
                        Operation.DIVISION -> presenter?.division()
                        Operation.EQUALITY -> presenter?.getTotal()
                        Operation.SUBTRACTION -> presenter?.subtraction()
                        Operation.MULTIPLICATION -> presenter?.multiplication()
                        Operation.DATA_CLEAR -> presenter?.clear()
                        Operation.MEMO_CLEAR -> presenter?.clearMemory()
                        Operation.MEMO_GET -> presenter?.getFromMemory()
                        Operation.MEMO_ADD -> presenter?.saveInMemory()
                        Operation.ADDITION -> presenter?.addition()
                    }
                } else {
                    if (model.keyboardButton.operation == Operation.POINT) {
                        presenter?.addPoint()
                    } else {
                        presenter?.addNumber(model.keyboardButton.name.toInt())
                    }
                }
            }
        })
        viewBinding?.let {
            recyclerView = it.rvList
            monitor = it.tvMonitor

        }
        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = keyboardAdapter
        }
        monitor!!.text = "sdfsdg"
        keyboardAdapter.setFields(generatePseudoButtons())
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    private fun generatePseudoButtons(): MutableList<KeyboardButton> {
        return mutableListOf<KeyboardButton>().apply {
            add(OperationCustomButton("M+", Operation.MEMO_ADD))
            add(OperationCustomButton("MR", Operation.MEMO_GET))
            add(OperationCustomButton("MC", Operation.MEMO_CLEAR))
            add(OperationCustomButton("C", Operation.DATA_CLEAR))

            add(DefaultCustomButton("1", Operation.DATA_ADD))
            add(DefaultCustomButton("2", Operation.DATA_ADD))
            add(DefaultCustomButton("3", Operation.DATA_ADD))
            add(OperationCustomButton("+", Operation.ADDITION))

            add(DefaultCustomButton("4", Operation.DATA_ADD))
            add(DefaultCustomButton("5", Operation.DATA_ADD))
            add(DefaultCustomButton("6", Operation.DATA_ADD))
            add(OperationCustomButton("-", Operation.SUBTRACTION))

            add(DefaultCustomButton("7", Operation.DATA_ADD))
            add(DefaultCustomButton("8", Operation.DATA_ADD))
            add(DefaultCustomButton("9", Operation.DATA_ADD))
            add(OperationCustomButton("x", Operation.MULTIPLICATION))

            add(DefaultCustomButton(".", Operation.POINT))
            add(DefaultCustomButton("0", Operation.DATA_ADD))
            add(OperationCustomButton("=", Operation.EQUALITY))
            add(OperationCustomButton("x", Operation.DIVISION))
        }
    }


    companion object {
        val TAG = CalculatorFragment.javaClass.canonicalName
        fun newInstance() = CalculatorFragment()
    }
}