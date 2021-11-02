package com.example.calculator2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator2.databinding.FragmentStartBinding
import kotlinx.android.synthetic.main.fragment_start.*
import net.objecthunter.exp4j.ExpressionBuilder

class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding
    private val RESULT_KEY = "result"
    private var result: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentStartBinding.bind(view)
        init()
        if (result?.isNotEmpty() == true) {
            binding.mathOperations.text = result
        }

    }

    private fun init(){
        binding.buttonZero.setOnClickListener {
            setTextFields("0")
        }
        binding.buttonOne.setOnClickListener {
            setTextFields("1")
        }
        binding.buttonTwo.setOnClickListener {
            setTextFields("2")
        }
        binding.buttonThree.setOnClickListener {
            setTextFields("3")
        }
        binding.buttonFour.setOnClickListener {
            setTextFields("4")
        }
        binding.buttonFive.setOnClickListener {
            setTextFields("5")
        }
        binding.buttonSix.setOnClickListener {
            setTextFields("6")
        }
        binding.buttonSeven.setOnClickListener {
            setTextFields("7")
        }
        binding.buttonEight.setOnClickListener {
            setTextFields("8")
        }
        binding.buttonNine.setOnClickListener {
            setTextFields("1")
        }
        binding.subtraction.setOnClickListener {

            setTextFields("-")
        }
        binding.addition.setOnClickListener {
            setTextFields("+")
        }
        binding.division.setOnClickListener {
            setTextFields("/")
        }
        binding.multiplication.setOnClickListener {
            setTextFields("*")
        }
        binding.clearButton.setOnClickListener {
            binding.mathOperations.text = ""
            binding.resultText.text = ""
        }
        binding.equality.setOnClickListener {
            if (binding.mathOperations.text.contains("/0")) {
                binding.mathOperations.text = getString(R.string.divisionByZero)
            } else {
                try {

                    val ex = ExpressionBuilder(binding.mathOperations.text.toString()).build()
                    val result = ex.evaluate()

                    val longRes = result.toLong()
                    if (result == longRes.toDouble()) {
                        binding.resultText.text = longRes.toString()
                    } else {
                        binding.resultText.text = result.toString()
                    }
                } catch (e: Exception) {
                    Log.e("Ошибка", "сообщение: ${e.message}")
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val result = binding.mathOperations.text.toString()
        outState.putString(RESULT_KEY, result)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState !== null) {
            result = savedInstanceState.getString(RESULT_KEY).toString()
        }
    }

    private fun setTextFields(str: String) {
        if (resultText.text != "") {
            binding.mathOperations.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperations.append(str)
    }

}