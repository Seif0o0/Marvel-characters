package totersapp.marvel.task.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelCharacterDto(
    val id :String,
    val name : String,
    val description:String,
    val thumbnail: ThumbnailDto
)
