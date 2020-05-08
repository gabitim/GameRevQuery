package com.app.model

import java.beans.ParameterDescriptor

/**
 * @author Timofti Gabriel
 */
data class Review (
    val review_title: String,
    val description: String,
    val text: String,
    val author: String,
    val belonging_game: Game
)
