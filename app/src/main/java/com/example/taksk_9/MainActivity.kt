package com.example.taksk_9

import DatabaseHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taksk_9.ui.theme.Taksk_9Theme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActionReceiver.register(this)
    }
    class MainActivity : AppCompatActivity() {

        private lateinit var recyclerView: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            recyclerView = this.findViewById(R.id.recyclerView)
            LinearLayoutManager(this).also { recyclerView.layoutManager = it }

            displayData()
        }

        private fun displayData() {
            val dbHelper = DatabaseHelper(this)
            val db = dbHelper.readableDatabase

            val cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                "${DatabaseHelper.COLUMN_ACTION_TIME} DESC"
            )

            val actions = mutableListOf<Action>()

            while (cursor.moveToNext()) {
                val action = Action(
                    type = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTION_TYPE)),
                    state = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTION_STATE)),
                    time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACTION_TIME))
                )
                actions.add(action)
            }

            cursor.close()
            db.close()

            val adapter = ActionAdapter(actions)
            recyclerView.adapter = adapter
        }
    }
    data class Action(
        val type: String,
        val state: String,
        val time: String
    )


}
