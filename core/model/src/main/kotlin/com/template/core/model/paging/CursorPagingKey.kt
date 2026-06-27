package com.template.core.model.paging

data class CursorPagingKey<out T>(val cursor: T?, val limit: Int)
