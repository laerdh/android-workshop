package no.netcompany.workshop.demo1.model

import java.time.LocalDate

data class TodoItem(val title: String = "",
                    val completed: Boolean = false,
                    val date: LocalDate = LocalDate.now())