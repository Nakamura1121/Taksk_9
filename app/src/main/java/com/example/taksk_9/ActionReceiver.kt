import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class ActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val actionType = intent.action
        val actionState = when (actionType) {
            Intent.ACTION_SCREEN_ON, Intent.ACTION_POWER_CONNECTED -> "ON"
            Intent.ACTION_SCREEN_OFF, Intent.ACTION_POWER_DISCONNECTED -> "OFF"
            else -> "UNKNOWN"
        }

        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())


        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ACTION_TYPE, actionType)
            put(DatabaseHelper.COLUMN_ACTION_STATE, actionState)
            put(DatabaseHelper.COLUMN_ACTION_TIME, currentTime)
        }

        db.insert(DatabaseHelper.TABLE_NAME, null, values)
        db.close()

        Log.d("ActionReceiver", "Action: $actionType, State: $actionState, Time: $currentTime")
    }

    companion object {
        fun register(context: Context) {
            val filter = IntentFilter().apply {
                addAction(Intent.ACTION_SCREEN_ON)
                addAction(Intent.ACTION_SCREEN_OFF)
                addAction(Intent.ACTION_POWER_CONNECTED)
                addAction(Intent.ACTION_POWER_DISCONNECTED)
            }

            val receiver = ActionReceiver()
            context.registerReceiver(receiver, filter)
        }
    }
}
