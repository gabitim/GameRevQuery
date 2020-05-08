package com.app.controller

import com.app.config.AppConfig
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Timofti Gabriel
 */

@RestController
@RequestMapping("/app")
class AppController(
    private val appConfig: AppConfig
) {

    @GetMapping("/version")
    fun getVersion(): String = appConfig.version
}
