package totersapp.marvel.task.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponse<T>(
    @Json(name = "code") val statusCode: Int,
    val status: String,
    val data: T
)