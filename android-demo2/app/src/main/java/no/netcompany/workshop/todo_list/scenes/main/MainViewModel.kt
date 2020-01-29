package no.netcompany.workshop.todo_list.scenes.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.netcompany.workshop.todo_list.db.TodoListDatabase
import no.netcompany.workshop.todo_list.model.TodoItem
import no.netcompany.workshop.todo_list.repository.TodoListRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoListRepository

    val todoList: LiveData<List<TodoItem>>

    init {
        val todoListDao = TodoListDatabase.getDatabase(application).todoListDao()
        repository = TodoListRepository(todoListDao)
        todoList = repository.allTodoItems
    }

    fun insert(title: String, description: String) = viewModelScope.launch {
        val todoItem = TodoItem(
            title = title,
            description = description
        )

        repository.insert(todoItem)
    }

    fun update(todoItem: TodoItem) = viewModelScope.launch {
        repository.update(todoItem)
    }

    fun delete(todoItem: TodoItem) = viewModelScope.launch {
        repository.delete(todoItem)
    }
}