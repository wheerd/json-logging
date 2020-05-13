package org.slf4j.impl

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

internal class JsonLoggerTest {
  private val logger = LoggerFactory.getLogger(this.javaClass)

  @Test
  fun log() {
    logger.info("This is a test")
  }
}
