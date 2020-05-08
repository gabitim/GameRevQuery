package com.app.service

import com.app.model.Bug
import com.app.repository.BugRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

/**
 * @author Timofti Gabriel
 */

@Service
class BugService(private val bugRepository: BugRepository){
    private val logger = KotlinLogging.logger {}

    fun addBug(bug: Bug): Bug {
        return bugRepository.save(bug)
    }

    fun updateBug(bug: Bug): Bug {
        return bugRepository.save(bug)
    }

    fun getBug(id: String): Bug? {
        return try {
            bugRepository.findById(id).get()
        } catch (e:NoSuchElementException) {
            logger.error {"Bug $id not found"}
            null
        }

    }
}