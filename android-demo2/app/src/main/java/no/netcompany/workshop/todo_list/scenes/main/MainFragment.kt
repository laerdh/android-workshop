package no.netcompany.workshop.todo_list.scenes.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import no.netcompany.workshop.todo_list.R
import no.netcompany.workshop.todo_list.model.TodoItem
import no.netcompany.workshop.todo_list.scenes.main.adapter.TodoListAdapter
import no.netcompany.workshop.todo_list.scenes.main.adapter.TodoListAdapterDelegate

class MainFragment: Fragment(R.layout.fragment_main), TodoListAdapterDelegate {

    private lateinit var viewModel: MainViewModel
    private lateinit var todoListAdapter: TodoListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupAdapter()
        setupObservers()
        setupListeners()
    }

    private fun setupAdapter() {
        todoListAdapter = TodoListAdapter()
        todoListAdapter.delegate = this

        todoList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }
    }

    private fun setupObservers() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.todoList.observe(this, Observer {
            todoListAdapter.items = it
        })
    }

    private fun setupListeners() {
        fab.setOnClickListener {
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_todo_item, null)

            val addTodoItemDialog = AlertDialog.Builder(requireContext())
                .setView(view)
                .create()

            val editTextTodoItemTitle = view.findViewById<EditText>(R.id.editTextTodoItemTitle)
            val editTextTodoItemDescription = view.findViewById<EditText>(R.id.editTextTodoItemDescription)
            val btnAddTodoItem = view.findViewById<Button>(R.id.btnAddTodoItem)

            btnAddTodoItem.setOnClickListener {
                viewModel.insert(
                    title = editTextTodoItemTitle.text.toString(),
                    description = editTextTodoItemDescription.text.toString()
                )
                addTodoItemDialog.dismiss()
            }

            addTodoItemDialog.show()
        }
    }

    override fun onTodoItemClicked(todoItem: TodoItem) {
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(todoItem)
        findNavController().navigate(action)
    }

    override fun onTodoItemCheckChanged(todoItem: TodoItem, isChecked: Boolean) {
        viewModel.update(todoItem.copy(completed = isChecked))

        val todoItemStatus = if (isChecked) getString(R.string.todo_item_status_completed) else getString(R.string.todo_item_status_todo)
        showToast("Marked \"${todoItem.title} as $todoItemStatus")
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}