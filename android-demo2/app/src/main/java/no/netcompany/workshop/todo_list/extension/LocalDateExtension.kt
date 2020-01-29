package no.netcompany.workshop.todo_list.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun LocalDate.ofLocalizedDate(): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    return this.format(formatter)
}