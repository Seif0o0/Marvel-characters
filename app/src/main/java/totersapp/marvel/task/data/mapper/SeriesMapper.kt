package totersapp.marvel.task.data.mapper

import totersapp.marvel.task.data.model.SeriesDto
import totersapp.marvel.task.domain.model.Series

fun SeriesDto.toSeries() = Series(
    id = id,
    title = title,
    description = description,
    startYear = startYear,
    endYear = endYear,
    thumbnail = thumbnailDto?.toThumbnail(),
)