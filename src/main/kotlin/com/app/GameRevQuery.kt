package com.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @author Timofti Gabriel
 */

@EnableSwagger2
@SpringBootApplication
class GameRevQuery

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<GameRevQuery>(*args)
    print("IT WORKS!!")
}
