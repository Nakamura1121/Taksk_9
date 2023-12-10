import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "actions.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "actions"
        const val COLUMN_ID = "_id"
        const val COLUMN_ACTION_TYPE = "action_type"
        const val COLUMN_ACTION_STATE = "action_state"
        const val COLUMN_ACTION_TIME = "action_time"
    }

    private val SQL_CREATE_ENTRIES = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_ACTION_TYPE TEXT,
            $COLUMN_ACTION_STATE TEXT,
            $COLUMN_ACTION_TIME TEXT
        )
    """.trimIndent()

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}
