package totersapp.marvel.task.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ErrorResponse(
    val code: Int,
    @Json(name = "status") val message: String
)