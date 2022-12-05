package totersapp.marvel.task.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesDto(
    val id: String,
    val title: String?,
    val description: String?,
    val startYear: String?,
    val endYear: String?,
    @Json(name = "thumbnail") val thumbnailDto: ThumbnailDto?
)