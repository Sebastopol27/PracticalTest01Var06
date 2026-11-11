package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PracticalTest01Var06Service : Service() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val score = intent?.getIntExtra(EXTRA_SCORE, 0) ?: 0

        if (score > SCORE_THRESHOLD) {
            handler.postDelayed({
                val timestamp = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(Date())

                val broadcastIntent = Intent(ACTION_VICTORY).apply {
                    setPackage(packageName)
                    putExtra(EXTRA_SCORE, score)
                    putExtra(EXTRA_TIMESTAMP, timestamp)
                }

                sendBroadcast(broadcastIntent)

                Log.d(
                    LOG_TAG,
                    "Broadcast Victory; timp=$timestamp; scor=$score"
                )
            }, BROADCAST_DELAY_MS)
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        Log.d(LOG_TAG, "Serviciul a fost oprit")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val ACTION_VICTORY =
            "ro.pub.cs.systems.eim.practicaltest01var06.VICTORY"

        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_TIMESTAMP = "extra_timestamp"

        private const val SCORE_THRESHOLD = 100
        private const val BROADCAST_DELAY_MS = 2_000L
        private const val LOG_TAG = "Var06Service"
    }
}