package com.template.core.model.paging

data class Page<out T, out K>(
    val items: List<T>,
    val nextKey: K?,
    val metadata: PayloadMetadata = PayloadMetadata(),
)
