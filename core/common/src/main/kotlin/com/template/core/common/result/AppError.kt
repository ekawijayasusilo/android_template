package com.template.core.common.result

sealed interface AppError {
    val message: String?
    val cause: Throwable?

    data class Network(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : AppError

    data class Remote(
        val code: String? = null,
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : AppError

    data class Authorization(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : AppError

    data class Validation(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : AppError

    data class Unknown(
        override val message: String? = null,
        override val cause: Throwable? = null,
    ) : AppError
}
