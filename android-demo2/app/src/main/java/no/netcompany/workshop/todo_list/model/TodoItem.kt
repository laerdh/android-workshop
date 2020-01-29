package no.netcompany.workshop.todo_list.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
@Entity(tableName = "todo_item_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "completed") val completed: Boolean = false,
    @ColumnInfo(name = "date") val date: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null) : Parcelable