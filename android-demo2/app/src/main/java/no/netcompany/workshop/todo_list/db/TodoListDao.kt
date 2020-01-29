package no.netcompany.workshop.todo_list.db

import androidx.lifecycle.LiveData
import androidx.room.*
import no.netcompany.workshop.todo_list.model.TodoItem

@Dao
interface TodoListDao {
    @Query("SELECT * FROM todo_item_table ORDER BY date, completed")
    fun getTodoList(): LiveData<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)

    @Update
    suspend fun update(todoItem: TodoItem)
}