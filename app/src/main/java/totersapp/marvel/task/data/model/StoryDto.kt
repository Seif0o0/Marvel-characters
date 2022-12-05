package totersapp.marvel.task.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class StoryDto(
    val id:String,
    val title:String?,
    val description:String?,
    val type:String?,
    @Json(name = "thumbnail") val thumbnailDto: ThumbnailDto?
)