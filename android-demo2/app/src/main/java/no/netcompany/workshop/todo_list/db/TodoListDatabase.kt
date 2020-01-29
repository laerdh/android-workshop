package no.netcompany.workshop.todo_list.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import no.netcompany.workshop.todo_list.model.TodoItem

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TodoListDatabase : RoomDatabase() {

    abstract fun todoListDao(): TodoListDao

    companion object {
        @Volatile
        private var INSTANCE: TodoListDatabase? = null

        fun getDatabase(context: Context): TodoListDatabase {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoListDatabase::class.java,
                    "todo_list_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}