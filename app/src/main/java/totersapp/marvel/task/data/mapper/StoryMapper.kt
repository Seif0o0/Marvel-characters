package totersapp.marvel.task.data.mapper

import com.squareup.moshi.Json
import totersapp.marvel.task.data.model.StoryDto
import totersapp.marvel.task.data.model.ThumbnailDto
import totersapp.marvel.task.domain.model.Story

fun StoryDto.toStory() = Story(
    id = id,
    title = title,
    description = description,
    type = type,
    thumbnail = thumbnailDto?.toThumbnail(),
)