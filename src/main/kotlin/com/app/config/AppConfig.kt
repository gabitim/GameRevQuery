package com.app.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * @author Timofti Gabriel
 */

@Configuration
@ConfigurationProperties(prefix = "game-rev-query")
class AppConfig {
    lateinit var version: String
}