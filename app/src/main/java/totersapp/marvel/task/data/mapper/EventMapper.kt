package totersapp.marvel.task.data.mapper

import totersapp.marvel.task.data.model.EventDto
import totersapp.marvel.task.domain.model.Event

fun EventDto.toEvent() = Event(
    id = id,
    title = title,
    description = description,
    startDate = startDate,
    endDate = endDate,
    thumbnail = thumbnailDto?.toThumbnail(),
)