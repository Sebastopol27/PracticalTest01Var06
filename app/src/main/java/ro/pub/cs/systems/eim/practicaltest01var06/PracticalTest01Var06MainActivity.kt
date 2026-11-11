package ro.pub.cs.systems.eim.practicaltest01var06

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class PracticalTest01Var06MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_main)

        val number1 = findViewById<EditText>(R.id.number1)
        val number2 = findViewById<EditText>(R.id.number2)
        val number3 = findViewById<EditText>(R.id.number3)

        val checkNumber1 = findViewById<CheckBox>(R.id.checkNumber1)
        val checkNumber2 = findViewById<CheckBox>(R.id.checkNumber2)
        val checkNumber3 = findViewById<CheckBox>(R.id.checkNumber3)

        val randomButton = findViewById<Button>(R.id.randomButton)

        randomButton.setOnClickListener {
            val options = listOf("1", "2", "3", "*")

            if (!checkNumber1.isChecked) {
                number1.setText(options.random())
            }

            if (!checkNumber2.isChecked) {
                number2.setText(options.random())
            }

            if (!checkNumber3.isChecked) {
                number3.setText(options.random())
            }

            val result =
                "${number1.text} | ${number2.text} | ${number3.text}"

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
    }
}