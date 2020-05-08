package com.app.controller

import com.app.model.BugDTO
import com.app.service.BugService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Timofti Gabriel
 */

@RestController
@RequestMapping("/games")
class BugController(
    private val bugService: BugService
) {

    /*@GetMapping
    fun getBugs(): List<BugDTO> =
        bugService.retrieveBugs()
            .map { BugDTO.from(it) }*/

    @GetMapping("/{id}")
    fun getBug(
        @PathVariable id: String
    ): ResponseEntity<BugDTO> =
        try {
            val bug = bugService.getBug(id)
            ResponseEntity.ok(BugDTO.from(bug!!))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }

    @PostMapping()
    fun addBug(
        @RequestBody bugDTO: BugDTO
    ): ResponseEntity<BugDTO> =
        try {
            val bug = bugService.addBug(bugDTO.toBug())
            ResponseEntity.ok(BugDTO.from(bug))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }

    @PutMapping
    fun updateBug(
        @RequestBody bugDTO: BugDTO
    ): ResponseEntity<BugDTO> =
        try {
            val bug = bugService.updateBug(bugDTO.toBug())
            ResponseEntity.ok(BugDTO.from(bug))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
}