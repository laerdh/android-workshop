package no.netcompany.workshop.todo_list.db

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun toDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString)
    }

    @TypeConverter
    fun toDateString(date: LocalDate): String {
        return date.toString()
    }
}