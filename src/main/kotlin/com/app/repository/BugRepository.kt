package com.app.repository

import com.app.model.Bug
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author Timofti Gabriel
 */
interface BugRepository : MongoRepository<Bug, String>