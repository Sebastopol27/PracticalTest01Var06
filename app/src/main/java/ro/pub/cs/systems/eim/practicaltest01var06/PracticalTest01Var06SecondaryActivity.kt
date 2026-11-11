package ro.pub.cs.systems.eim.practicaltest01var06

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class PracticalTest01Var06SecondaryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_secondary)

        val gainText = findViewById<TextView>(R.id.gainText)

        val number1 = intent.getStringExtra(EXTRA_NUMBER_1) ?: ""
        val number2 = intent.getStringExtra(EXTRA_NUMBER_2) ?: ""
        val number3 = intent.getStringExtra(EXTRA_NUMBER_3) ?: ""
        val checkedCount = intent.getIntExtra(EXTRA_CHECKED_COUNT, 0)

        val nonJokers = listOf(number1, number2, number3)
            .filter { it != "*" }

        val won = nonJokers.distinct().size <= 1

        if (won) {
            val gain = when (checkedCount) {
                0 -> 100
                1 -> 50
                2 -> 10
                else -> 0
            }

            gainText.text = "Gained: $gain"
        } else {
            gainText.text = ""
        }
    }

    companion object {
        const val EXTRA_NUMBER_1 = "number_1"
        const val EXTRA_NUMBER_2 = "number_2"
        const val EXTRA_NUMBER_3 = "number_3"
        const val EXTRA_CHECKED_COUNT = "checked_count"
    }
}