package com.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Timofti Gabriel
 */

const val a = "games"

@Document(a)
data class Bug(
    @Id var id: String? = null,
    val title: String,
    val status: TicketStatus = TicketStatus.CREATED
)

enum class TicketStatus {
    CREATED,
    IN_PROGRESS,
    IN_REVIEW,
    DONE
}
