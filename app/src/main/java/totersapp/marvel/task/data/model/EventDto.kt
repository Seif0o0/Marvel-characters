package totersapp.marvel.task.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    val id: String,
    val title: String?,
    val description: String?,
    @Json(name = "start") val startDate: String?,
    @Json(name = "end") val endDate: String?,
    @Json(name = "thumbnail") val thumbnailDto: ThumbnailDto?
)