package totersapp.marvel.task.domain.model

data class MarvelCharacter(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)
