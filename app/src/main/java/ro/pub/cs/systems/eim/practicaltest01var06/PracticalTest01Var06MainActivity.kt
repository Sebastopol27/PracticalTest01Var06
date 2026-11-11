package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class PracticalTest01Var06MainActivity : ComponentActivity() {

    private var score = 0

    private val secondaryActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val gain = result.data?.getIntExtra(
                    PracticalTest01Var06SecondaryActivity.EXTRA_GAIN,
                    0
                ) ?: 0

                score += gain

                Toast.makeText(
                    this,
                    "Scor: $score",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        score = savedInstanceState?.getInt(KEY_SCORE, 0) ?: 0

        setContentView(R.layout.activity_practical_test01_var06_main)

        val number1 = findViewById<EditText>(R.id.number1)
        val number2 = findViewById<EditText>(R.id.number2)
        val number3 = findViewById<EditText>(R.id.number3)

        val checkNumber1 = findViewById<CheckBox>(R.id.checkNumber1)
        val checkNumber2 = findViewById<CheckBox>(R.id.checkNumber2)
        val checkNumber3 = findViewById<CheckBox>(R.id.checkNumber3)

        val playButton = findViewById<Button>(R.id.playButton)
        val randomButton = findViewById<Button>(R.id.randomButton)

        randomButton.setOnClickListener {
            val options = listOf("1", "2", "3", "*")

            if (!checkNumber1.isChecked) number1.setText(options.random())
            if (!checkNumber2.isChecked) number2.setText(options.random())
            if (!checkNumber3.isChecked) number3.setText(options.random())

            Toast.makeText(
                this,
                "${number1.text} | ${number2.text} | ${number3.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        playButton.setOnClickListener {
            val checkedCount = listOf(
                checkNumber1,
                checkNumber2,
                checkNumber3
            ).count { it.isChecked }

            val intent = Intent(
                this,
                PracticalTest01Var06SecondaryActivity::class.java
            ).apply {
                putExtra(
                    PracticalTest01Var06SecondaryActivity.EXTRA_NUMBER_1,
                    number1.text.toString()
                )
                putExtra(
                    PracticalTest01Var06SecondaryActivity.EXTRA_NUMBER_2,
                    number2.text.toString()
                )
                putExtra(
                    PracticalTest01Var06SecondaryActivity.EXTRA_NUMBER_3,
                    number3.text.toString()
                )
                putExtra(
                    PracticalTest01Var06SecondaryActivity.EXTRA_CHECKED_COUNT,
                    checkedCount
                )
            }

            secondaryActivityLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SCORE, score)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val KEY_SCORE = "score"
    }
}