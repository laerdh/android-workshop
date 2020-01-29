package no.netcompany.workshop.todo_list.scenes.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_todo_item_details.*
import no.netcompany.workshop.todo_list.R
import no.netcompany.workshop.todo_list.extension.ofLocalizedDate
import no.netcompany.workshop.todo_list.scenes.main.MainViewModel

class TodoItemDetailsFragment : Fragment(R.layout.fragment_todo_item_details) {

    private val args: TodoItemDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        showTodoItemDetails()
        setupListeners()
    }

    private fun setupListeners() {
        buttonDelete.setOnClickListener {
            viewModel.delete(args.todoItem)
            findNavController().popBackStack()
        }
    }

    private fun showTodoItemDetails() {
        val todoItem = args.todoItem

        textViewTitle.text = todoItem.title
        textViewDate.text = todoItem.date.ofLocalizedDate()
        textViewStatus.text = if (todoItem.completed) getString(R.string.todo_item_status_completed) else getString(R.string.todo_item_status_todo)

        if (todoItem.description.isNullOrEmpty()) {
            textViewDescription.visibility = View.GONE
            textViewDescriptionLabel.visibility = View.GONE
        } else {
            textViewDescription.text = todoItem.description
        }
    }
}
