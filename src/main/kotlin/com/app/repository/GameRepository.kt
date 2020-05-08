package com.app.repository

import com.app.model.Game
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Timofti Gabriel
 */
interface GameRepository : MongoRepository<Game, String>