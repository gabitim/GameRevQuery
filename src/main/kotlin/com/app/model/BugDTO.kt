package com.app.model

/**
 * @author Timofti Gabriel
 */

data class BugDTO(
    var id: String? = null,
    val title: String,
    val status: TicketStatusDTO = TicketStatusDTO.CREATED
) {

    fun toBug(): Bug =
        Bug(
            id = id,
            title = title,
            status = status.toStatus()
        )

    companion object {
        fun from(bug: Bug): BugDTO =
            BugDTO(
                id = bug.id,
                title = bug.title,
                status = TicketStatusDTO.from(bug.status)
            )
    }
}

enum class TicketStatusDTO {
    CREATED,
    IN_PROGRESS,
    IN_REVIEW,
    DONE;

    fun toStatus(): TicketStatus =
        TicketStatus.valueOf(this.name)

    companion object {
        fun from(ticketStatus: TicketStatus): TicketStatusDTO =
            TicketStatusDTO.valueOf(ticketStatus.name)
    }
}