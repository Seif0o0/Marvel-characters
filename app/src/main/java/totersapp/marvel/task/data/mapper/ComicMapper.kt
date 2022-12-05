package totersapp.marvel.task.data.mapper

import totersapp.marvel.task.data.model.ComicDto
import totersapp.marvel.task.domain.model.Comic

fun ComicDto.toComic() = Comic(
    id = id,
    digitalId = digitalId,
    title = title,
    description = description,
    variantDescription = variantDescription,
    pageCount = pageCount ?: 0,
    thumbnail = thumbnailDto?.toThumbnail(),
)