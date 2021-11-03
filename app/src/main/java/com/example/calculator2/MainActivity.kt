package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var result: String? = null

    companion object {
        private const val RESULT_KEY = "result_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        if (result?.isNotEmpty() == true) {
            binding.mathOperations.text = result
        }
    }

    private fun init() {
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
                val expression = ExpressionBuilder(binding.mathOperations.text.toString()).build()
                val result = expression.evaluate()
                val longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    binding.resultText.text = longRes.toString()
                } else {
                    binding.resultText.text = result.toString()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val result = binding.mathOperations.text.toString()
        outState.putString(RESULT_KEY, result)
    }

    private fun setTextFields(str: String) {
        if (resultText.text.isNotEmpty()) {
            binding.mathOperations.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperations.append(str)
    }
}