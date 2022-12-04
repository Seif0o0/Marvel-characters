package totersapp.marvel.task.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailDto(
    @Json(name = "path") val url: String,
    val extension: String
)