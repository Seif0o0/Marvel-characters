package totersapp.marvel.task.domain.model

data class Comic(
    val id: String,
    val digitalId: String,
    val title: String?,
    val description: String?,
    val variantDescription: String?,
    val pageCount: Int,
    val thumbnail: Thumbnail?
)