package totersapp.marvel.task.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ErrorResponse(
    val code: String,
    val message: String?,
    val status: String?
)