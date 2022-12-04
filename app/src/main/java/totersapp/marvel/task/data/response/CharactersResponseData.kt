package totersapp.marvel.task.data.response

import com.squareup.moshi.JsonClass
import totersapp.marvel.task.data.model.MarvelCharacterDto

@JsonClass(generateAdapter = true)
class CharactersResponseData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<MarvelCharacterDto>
)