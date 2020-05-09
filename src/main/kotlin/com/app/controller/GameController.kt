package com.app.controller


import com.app.model.Game
import com.app.model.Key
import com.app.model.Review
import com.app.service.GameService
import org.litote.kmongo.MongoOperator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Timofti Gabriel
 */

@RestController
@RequestMapping("/games")
class GameController(
    private val gameService: GameService
) {

    @GetMapping
    fun getGames(): List<Game>? =
        gameService.showAllGames()

    @GetMapping("/{id}")
    fun getGame(@PathVariable id: String): ResponseEntity<Game> =
        try {
            val game = gameService.showGameReviews(id)
            ResponseEntity.ok(game)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }

    @PostMapping("/{game_title}")
    fun addGame(@PathVariable game_title: String): ResponseEntity<Game> =
        try {

            val game = gameService.addGame(Game() , game_title)
            ResponseEntity.ok(game)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }

    @PutMapping("/{game_title}")
    fun addReview(@RequestBody review: Review, @PathVariable game_title: String) : ResponseEntity<Game> =
        try {
            val game = gameService.addReview(review, game_title)
            ResponseEntity.ok(game)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }

    @PatchMapping("/{game_title}")
    fun updateReview(@RequestBody review: Review, @PathVariable game_title: String) : ResponseEntity<Review> =
        try {
            val review = gameService.updateReview(review, game_title)
            ResponseEntity.ok(review)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping("/{id}")
    fun deleteGame(@RequestBody key: Key, @PathVariable id: String) : List<Game>? =
        gameService.deleteGame(id, key)

    @DeleteMapping("/{id}/{title_review}")
    fun deleteReview(@PathVariable id: String, @PathVariable title_review: String) : Game? =
        gameService.deleteReview(id, title_review)
}
