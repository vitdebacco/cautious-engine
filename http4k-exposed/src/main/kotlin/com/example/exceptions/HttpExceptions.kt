package com.example.exceptions

import org.http4k.core.Status

sealed class HttpException(val status: Status, message: String, throwable: Throwable?) : Exception(message, throwable)

sealed class FieldErrorsHttpException(
    status: Status,
    message: String,
    val errors: Map<String, Any>,
    throwable: Throwable?
) : HttpException(status, message, throwable)

class BadRequestException(message: String, throwable: Throwable? = null) :
    HttpException(Status.BAD_REQUEST, message, throwable)

class UnauthorizedException(message: String, throwable: Throwable? = null) :
    HttpException(Status.UNAUTHORIZED, message, throwable)

class ForbiddenException(message: String, throwable: Throwable? = null) :
    HttpException(Status.FORBIDDEN, message, throwable)

class NotFoundException(message: String = "not found", throwable: Throwable? = null) :
    HttpException(Status.NOT_FOUND, message, throwable)

class ConflictException(message: String, throwable: Throwable? = null) :
    HttpException(Status.CONFLICT, message, throwable)

class UnprocessableEntityException(message: String, errors: Map<String, Any>, throwable: Throwable? = null) :
    FieldErrorsHttpException(Status.UNPROCESSABLE_ENTITY, message, errors, throwable)

class UnknownException(message: String, throwable: Throwable? = null) :
    HttpException(Status.INTERNAL_SERVER_ERROR, message, throwable)