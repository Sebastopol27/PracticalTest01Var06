package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class PracticalTest01Var06SecondaryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_secondary)

        val gainText = findViewById<TextView>(R.id.gainText)

        val numbers = listOf(
            intent.getStringExtra(EXTRA_NUMBER_1) ?: "",
            intent.getStringExtra(EXTRA_NUMBER_2) ?: "",
            intent.getStringExtra(EXTRA_NUMBER_3) ?: ""
        )

        val checkedCount = intent.getIntExtra(EXTRA_CHECKED_COUNT, 0)
        val validNumbers = numbers.all { it.isNotBlank() }
        val nonJokers = numbers.filter { it != "*" }
        val won = validNumbers && nonJokers.distinct().size <= 1

        val gain = if (won) {
            when (checkedCount) {
                0 -> 100
                1 -> 50
                2 -> 10
                else -> 0
            }
        } else {
            0
        }

        gainText.text = if (won) "Gained: $gain" else "No gain"

        val resultIntent = Intent().apply {
            putExtra(EXTRA_GAIN, gain)
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }

    companion object {
        const val EXTRA_NUMBER_1 = "number_1"
        const val EXTRA_NUMBER_2 = "number_2"
        const val EXTRA_NUMBER_3 = "number_3"
        const val EXTRA_CHECKED_COUNT = "checked_count"
        const val EXTRA_GAIN = "gain"
    }
}