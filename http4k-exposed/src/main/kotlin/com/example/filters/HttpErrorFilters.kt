package com.example.filters

import com.example.exceptions.FieldErrorsHttpException
import com.example.exceptions.HttpException
import com.example.models.api.ErrorResponse
import org.http4k.core.Body
import org.http4k.core.Filter
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.slf4j.LoggerFactory

object ExceptionResolver {
    private val logger = LoggerFactory.getLogger(javaClass)

    operator fun invoke() = Filter { next -> {
            try {
                next(it)
            } catch (e: FieldErrorsHttpException) {
                logger.error("${e.message}")
                logger.debug("${e.message}", e)
                createErrorResponse(e.status, e.message, e.errors)
            } catch (e: HttpException) {
                logger.error("${e.message}")
                logger.debug("${e.message}", e)
                createErrorResponse(e.status, e.message)
            } catch (t: Throwable) {
                logger.error("${t.message}")
                logger.debug("${t.message}", t)
                createErrorResponse(Status.INTERNAL_SERVER_ERROR, "an unknown error occurred: ${t.javaClass}")
            }
        }
    }

}

private val error = Body.auto<ErrorResponse>().toLens()

fun createErrorResponse(status: Status, message: String?, errors: Map<String, Any> = emptyMap()) =
    Response(status).with(error of ErrorResponse(message ?: "", errors))