package com.app.service


import com.app.model.Game
import com.app.model.Key
import com.app.model.Popularity
import com.app.model.Review
import com.app.repository.GameRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.litote.kmongo.*

/**
 * @author Timofti Gabriel
 */

@Service
class GameService(private val gameRepository: GameRepository){
    private val logger = KotlinLogging.logger {}

    val client = KMongo.createClient()
    val database = client.getDatabase("games")
    val collection = database.getCollection<Game>("all-games")


    fun addGame(game: Game, title: String) : Game? {
        try {
            game.title = title
            game.nr_of_reviews = 0
            game.reviews = ArrayList()
            game.popularity = Popularity.NEW

            return gameRepository.save(game)
        }
        catch (e: NoSuchElementException) {
            logger.error { "IN PUT $title not found" }
            return null
        }
    }

    fun addReview(review: Review, game_title: String) : Game? {
        try {
            var updReviews = collection.findOne(Game::title eq game_title)?.nr_of_reviews?.plus(1)

            collection.updateOne(Game::title eq game_title, addToSet(Game::reviews, review))
            collection.updateOne(Game::title eq game_title, setValue(Game::popularity, Popularity.HOT))
            collection.updateOne(Game::title eq game_title, setValue(Game::nr_of_reviews, updReviews))

            return collection.findOne(Game::title eq game_title)
        }
        catch (e: NoSuchElementException) {
            logger.error { "IN PUT $game_title not found" }
            return null
        }
    }

    fun updateReview(review: Review, game_title: String) : Review? {
        try {
            var allReviews = collection.findOne(Game::title eq game_title)?.reviews

            if (allReviews != null) {
                for (it in allReviews) {
                    if (it.review_title == review.review_title) {
                        it.review_title = review.review_title
                        it.author = review.author
                        it.description = review.description
                        it.text = review.text

                        break
                    }
                }
            }
            else{
                return null
            }

            collection.updateOne(Game::title eq game_title, setValue(Game::reviews, allReviews))

            return review
        }
        catch (e: NoSuchElementException) {
            logger.error { "IN PUT $game_title not found" }
            return null
        }
    }

    fun showGameReviews(id: String): Game? {
        return try {
            gameRepository.findById(id).get()
        } catch (e:NoSuchElementException) {
            logger.error {"Game with $id not found"}
            null
        }
    }

    fun showAllGames() : List<Game>? {
        try {
            var games: List<Game> = emptyList()
            games = gameRepository.findAll()
            return games
        } catch (e: NoSuchElementException) {
            logger.error { "YEAHH" }
            return null
        }
    }

    fun deleteGame(id: String, key: Key) : List<Game>? {
        return try {
            if (key.authKey == gameRepository.findById(id).get().key.authKey) {
                gameRepository.deleteById(id)
                return gameRepository.findAll()
            }
            else
            {
                logger.error { "WRONG AUTH KEY!!!" }
                return null
            }
        }
        catch (e: NoSuchElementException) {
            logger.error { "YEAHH" }
            return null
        }
    }
}