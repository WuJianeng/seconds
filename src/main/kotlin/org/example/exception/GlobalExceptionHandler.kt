package org.example.exception

import org.example.result.CodeMsg
import org.example.result.Result
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.lang.Exception
import java.net.BindException
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(request: HttpServletRequest, e: Exception): Result<*> {
        // log 打印日志
        log.info(e.stackTrace.contentToString())
        e.printStackTrace()
        val codeMsg = if (e is BindException) {
            val errors = e.message
             if (errors == null) {
                CodeMsg.BIND_ERROR
            } else {
                CodeMsg.BIND_ERROR.fillArgs(errors)
            }
        } else if (e is GlobalException) {
          e.codeMsg
        } else {
            CodeMsg.SERVER_ERROR
        }
        return Result.error<String>(codeMsg)
    }
}