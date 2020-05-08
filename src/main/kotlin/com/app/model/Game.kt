package com.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Timofti Gabriel
 */


@Document(collection = "all-games")
data class Game(
    @Id var id: String? = (0..1000).random().toString(),
    var title: String?,
    var nr_of_reviews: Int = 0,
    var reviews: ArrayList<Review>?,
    var popularity: Popularity = Popularity.NEW
)

enum class Popularity {
    NEW,
    HOT,
    LOW,
    MOST_DISCUSSED
}
