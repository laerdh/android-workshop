package no.netcompany.workshop.demo1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.netcompany.workshop.demo1.R
import no.netcompany.workshop.demo1.model.TodoItem

class ExampleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<TodoItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExampleItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_example, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ExampleItemViewHolder -> holder.bind(item)
        }
    }

    inner class ExampleItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: TodoItem) {
            itemView.findViewById<TextView>(R.id.textViewTitle).apply {
                text = item.title
            }
            itemView.findViewById<TextView>(R.id.textViewDate).apply {
                text = item.date.toString()
            }
            itemView.findViewById<CheckBox>(R.id.checkboxCompleted).apply {
                isChecked = item.completed
            }
        }
    }
}