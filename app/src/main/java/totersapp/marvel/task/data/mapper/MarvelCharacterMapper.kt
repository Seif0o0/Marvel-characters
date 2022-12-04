package totersapp.marvel.task.data.mapper

import totersapp.marvel.task.data.model.MarvelCharacterDto
import totersapp.marvel.task.domain.model.MarvelCharacter

fun MarvelCharacterDto.toMarvelCharacter() = MarvelCharacter(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail.toThumbnail()
)