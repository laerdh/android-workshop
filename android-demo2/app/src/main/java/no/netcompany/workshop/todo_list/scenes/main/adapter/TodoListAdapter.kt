package no.netcompany.workshop.todo_list.scenes.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_todo.view.*
import no.netcompany.workshop.todo_list.R
import no.netcompany.workshop.todo_list.model.TodoItem
import no.netcompany.workshop.todo_list.extension.ofLocalizedDate

interface TodoListAdapterDelegate {
    fun onTodoItemClicked(todoItem: TodoItem)
    fun onTodoItemCheckChanged(todoItem: TodoItem, isChecked: Boolean)
}

class TodoListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var delegate: TodoListAdapterDelegate? = null

    var items: List<TodoItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when (holder) {
            is TodoItemViewHolder -> holder.bind(item)
        }
    }

    inner class TodoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(todoItem: TodoItem) {
            itemView.setOnClickListener(null)
            itemView.todoItemCheckbox.setOnCheckedChangeListener(null)

            itemView.todoItemDate.text = todoItem.date.ofLocalizedDate()
            itemView.todoItemTitle.text = todoItem.title
            itemView.todoItemCheckbox.isChecked = todoItem.completed

            itemView.setOnClickListener {
                delegate?.onTodoItemClicked(todoItem)
            }

            itemView.todoItemCheckbox.setOnCheckedChangeListener { _, isChecked ->
                delegate?.onTodoItemCheckChanged(todoItem, isChecked)
            }
        }
    }
}