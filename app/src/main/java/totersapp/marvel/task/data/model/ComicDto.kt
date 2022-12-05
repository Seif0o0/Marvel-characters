package totersapp.marvel.task.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicDto(
    val id: String,
    val digitalId: String,
    val title: String?,
    val description: String?,
    val variantDescription: String?,
    val pageCount: Int?,
    @Json(name = "thumbnail") val thumbnailDto: ThumbnailDto?
)