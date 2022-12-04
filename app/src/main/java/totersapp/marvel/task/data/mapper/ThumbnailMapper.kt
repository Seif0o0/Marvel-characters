package totersapp.marvel.task.data.mapper

import totersapp.marvel.task.data.model.ThumbnailDto
import totersapp.marvel.task.domain.model.Thumbnail

fun ThumbnailDto.toThumbnail() = Thumbnail(
    url = url,
    extension = extension
)