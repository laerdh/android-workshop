package no.netcompany.workshop.todo_list.repository

import androidx.lifecycle.LiveData
import no.netcompany.workshop.todo_list.db.TodoListDao
import no.netcompany.workshop.todo_list.model.TodoItem

class TodoListRepository(private val todoListDao: TodoListDao) {

    val allTodoItems: LiveData<List<TodoItem>> = todoListDao.getTodoList()

    suspend fun insert(todoItem: TodoItem) {
        todoListDao.insert(todoItem)
    }

    suspend fun delete(todoItem: TodoItem) {
        todoListDao.delete(todoItem)
    }

    suspend fun update(todoItem: TodoItem) {
        todoListDao.update(todoItem)
    }
}