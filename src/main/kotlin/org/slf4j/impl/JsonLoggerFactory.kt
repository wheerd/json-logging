package org.slf4j.impl

import org.slf4j.ILoggerFactory

class JsonLoggerFactory : ILoggerFactory {
  private val minLogLevel = lazy {
    LogLevel.valueOf(System.getenv("LOG_LEVEL") ?: LogLevel.Debug.name)
  }

  override fun getLogger(name: String) = JsonLogger(minLevel = minLogLevel.value, loggerName = name)
}
