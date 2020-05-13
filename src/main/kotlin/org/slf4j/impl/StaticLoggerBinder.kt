package org.slf4j.impl

import org.slf4j.spi.LoggerFactoryBinder

@Suppress("Unused")
class StaticLoggerBinder : LoggerFactoryBinder {
  companion object {
    @JvmStatic
    val singleton = StaticLoggerBinder()
  }

  override fun getLoggerFactory() = JsonLoggerFactory()

  override fun getLoggerFactoryClassStr() = JsonLoggerFactory::javaClass.name
}
