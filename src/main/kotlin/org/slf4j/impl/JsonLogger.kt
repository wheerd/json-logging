package org.slf4j.impl

import org.slf4j.Logger
import org.slf4j.Marker
import java.io.PrintStream
import kotlinx.serialization.json.*
import java.io.PrintWriter
import java.io.StringWriter
import java.time.Instant


class JsonLogger(
  private val loggerName: String,
  private val minLevel: LogLevel,
  private val out: PrintStream = System.out
) : Logger {

  private val json = Json(JsonConfiguration.Stable)

  private fun log(level: LogLevel, message: String, throwable: Throwable) {
    val stackTrace = StringWriter().let {
      throwable.printStackTrace(PrintWriter(it))
      toString()
    }

    val jsonMessage = json {
      "message" to message
      "level" to level.name.toLowerCase()
      "exceptionMessage" to throwable.message
      "stackTrace" to stackTrace
      "timestamp" to Instant.now().toString()
      "source" to loggerName
    }

    val jsonData = json.stringify(JsonObject.serializer(), jsonMessage)
    out.println(jsonData)
  }

  private fun log(level: LogLevel, message: String) {
    val jsonMessage = json {
      "message" to message
      "level" to level.name.toLowerCase()
      "timestamp" to Instant.now().toString()
      "source" to loggerName
    }

    val jsonData = json.stringify(JsonObject.serializer(), jsonMessage)
    out.println(jsonData)
  }

  override fun getName() = loggerName

  override fun isWarnEnabled() = minLevel >= LogLevel.Warn
  override fun isWarnEnabled(marker: Marker?) = isWarnEnabled()
  override fun warn(msg: String) = log(LogLevel.Warn, msg)
  override fun warn(format: String, arg: Any?) = log(LogLevel.Warn, format.format(arg))
  override fun warn(format: String, vararg arguments: Any?) = log(LogLevel.Warn, format.format(*arguments))
  override fun warn(format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Warn, format.format(arg1, arg2))
  override fun warn(msg: String, t: Throwable) = log(LogLevel.Warn, msg, t)
  override fun warn(marker: Marker?, msg: String) = log(LogLevel.Warn, msg)
  override fun warn(marker: Marker?, format: String, arg: Any?) = log(LogLevel.Warn, format.format(arg))
  override fun warn(marker: Marker?, format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Warn, format.format(arg1, arg2))
  override fun warn(marker: Marker?, format: String, vararg arguments: Any?) = log(LogLevel.Warn, format.format(*arguments))
  override fun warn(marker: Marker?, msg: String, t: Throwable) = log(LogLevel.Warn, msg, t)

  override fun isInfoEnabled() = minLevel >= LogLevel.Info
  override fun isInfoEnabled(marker: Marker?) = isInfoEnabled()
  override fun info(msg: String) = log(LogLevel.Info, msg)
  override fun info(format: String, arg: Any?) = log(LogLevel.Info, format.format(arg))
  override fun info(format: String, vararg arguments: Any?) = log(LogLevel.Info, format.format(*arguments))
  override fun info(format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Info, format.format(arg1, arg2))
  override fun info(msg: String, t: Throwable) = log(LogLevel.Info, msg, t)
  override fun info(marker: Marker?, msg: String) = log(LogLevel.Info, msg)
  override fun info(marker: Marker?, format: String, arg: Any?) = log(LogLevel.Info, format.format(arg))
  override fun info(marker: Marker?, format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Info, format.format(arg1, arg2))
  override fun info(marker: Marker?, format: String, vararg arguments: Any?) = log(LogLevel.Info, format.format(*arguments))
  override fun info(marker: Marker?, msg: String, t: Throwable) = log(LogLevel.Info, msg, t)

  override fun isErrorEnabled() = minLevel >= LogLevel.Error
  override fun isErrorEnabled(marker: Marker?) = isErrorEnabled()
  override fun error(msg: String) = log(LogLevel.Error, msg)
  override fun error(format: String, arg: Any?) = log(LogLevel.Error, format.format(arg))
  override fun error(format: String, vararg arguments: Any?) = log(LogLevel.Error, format.format(*arguments))
  override fun error(format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Error, format.format(arg1, arg2))
  override fun error(msg: String, t: Throwable) = log(LogLevel.Error, msg, t)
  override fun error(marker: Marker?, msg: String) = log(LogLevel.Error, msg)
  override fun error(marker: Marker?, format: String, arg: Any?) = log(LogLevel.Error, format.format(arg))
  override fun error(marker: Marker?, format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Error, format.format(arg1, arg2))
  override fun error(marker: Marker?, format: String, vararg arguments: Any?) = log(LogLevel.Error, format.format(*arguments))
  override fun error(marker: Marker?, msg: String, t: Throwable) = log(LogLevel.Error, msg, t)

  override fun isDebugEnabled() = minLevel >= LogLevel.Debug
  override fun isDebugEnabled(marker: Marker?) = isDebugEnabled()
  override fun debug(msg: String) = log(LogLevel.Debug, msg)
  override fun debug(format: String, arg: Any?) = log(LogLevel.Debug, format.format(arg))
  override fun debug(format: String, vararg arguments: Any?) = log(LogLevel.Debug, format.format(*arguments))
  override fun debug(format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Debug, format.format(arg1, arg2))
  override fun debug(msg: String, t: Throwable) = log(LogLevel.Debug, msg, t)
  override fun debug(marker: Marker?, msg: String) = log(LogLevel.Debug, msg)
  override fun debug(marker: Marker?, format: String, arg: Any?) = log(LogLevel.Debug, format.format(arg))
  override fun debug(marker: Marker?, format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Debug, format.format(arg1, arg2))
  override fun debug(marker: Marker?, format: String, vararg arguments: Any?) = log(LogLevel.Debug, format.format(*arguments))
  override fun debug(marker: Marker?, msg: String, t: Throwable) = log(LogLevel.Debug, msg, t)

  override fun isTraceEnabled() = minLevel >= LogLevel.Trace
  override fun isTraceEnabled(marker: Marker?) = isTraceEnabled()
  override fun trace(msg: String) = log(LogLevel.Trace, msg)
  override fun trace(format: String, arg: Any?) = log(LogLevel.Trace, format.format(arg))
  override fun trace(format: String, vararg arguments: Any?) = log(LogLevel.Trace, format.format(*arguments))
  override fun trace(format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Trace, format.format(arg1, arg2))
  override fun trace(msg: String, t: Throwable) = log(LogLevel.Trace, msg, t)
  override fun trace(marker: Marker?, msg: String) = log(LogLevel.Trace, msg)
  override fun trace(marker: Marker?, format: String, arg: Any?) = log(LogLevel.Trace, format.format(arg))
  override fun trace(marker: Marker?, format: String, arg1: Any?, arg2: Any?) = log(LogLevel.Trace, format.format(arg1, arg2))
  override fun trace(marker: Marker?, format: String, vararg arguments: Any?) = log(LogLevel.Trace, format.format(*arguments))
  override fun trace(marker: Marker?, msg: String, t: Throwable) = log(LogLevel.Trace, msg, t)
}
