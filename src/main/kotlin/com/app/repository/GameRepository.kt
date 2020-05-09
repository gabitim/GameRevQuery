package com.app.repository

import com.app.model.Game
import org.bson.conversions.Bson
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author Timofti Gabriel
 */
interface GameRepository : MongoRepository<Game, String>
