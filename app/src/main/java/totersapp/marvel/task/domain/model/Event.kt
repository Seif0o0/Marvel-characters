package totersapp.marvel.task.domain.model

data class Event(
    val id: String,
    val title: String?,
    val description: String?,
    val startDate: String?,
    val endDate: String?,
    val thumbnail: Thumbnail?
)