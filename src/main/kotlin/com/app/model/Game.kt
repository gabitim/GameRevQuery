package com.app.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Timofti Gabriel
 */


@Document(collection = "all-games")
data class Game(
    @Id val id: String? = (0..1000).random().toString(),
    var title: String? = null,
    var nr_of_reviews: Int = 0,
    var reviews: ArrayList<Review>? = null,
    var popularity: Popularity = Popularity.NEW,
    val key : Key = Key()
)

data class Key(
    val authKey : String = "YourSecretKey!"
)

enum class Popularity {
    NEW,
    HOT,
    LOW,
    MOST_DISCUSSED
}
