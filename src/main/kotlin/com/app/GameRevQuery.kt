package com.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Timofti Gabriel
 */

@SpringBootApplication
class GameRevQuery

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<GameRevQuery>(*args)
    print("IT WORKS!!")
}
