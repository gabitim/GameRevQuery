package com.app.service


import com.app.model.Game
import com.app.model.Popularity
import com.app.model.Review
import com.app.repository.GameRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

/**
 * @author Timofti Gabriel
 */

@Service
class GameService(private val gameRepository: GameRepository){
    private val logger = KotlinLogging.logger {}

    fun addGame(game: Game, title: String): Game {
        game.title = title
        game.nr_of_reviews = 0
        game.reviews = ArrayList()
        game.popularity = Popularity.NEW

        return gameRepository.save(game)
    }

    fun addReview(review: Review): Game {
        review.belonging_game.reviews?.add(review)
        review.belonging_game.nr_of_reviews ++

        return gameRepository.save(review.belonging_game)
    }

    fun showGameReview(title: String): Game? {
        return try {
            gameRepository.findById(title).get()
        } catch (e:NoSuchElementException) {
            logger.error {"Game $title not found"}
            null
        }

    }
}