package com.github.mvukic.model

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val userId: String,
    val id: Long,
    val title: String,
    val completed: Boolean
)