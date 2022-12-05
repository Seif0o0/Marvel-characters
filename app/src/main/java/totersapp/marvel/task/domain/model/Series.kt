package totersapp.marvel.task.domain.model

data class Series(
    val id: String,
    val title: String?,
    val description: String?,
    val startYear: String?,
    val endYear: String?,
    val thumbnail: Thumbnail?
)