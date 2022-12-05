package totersapp.marvel.task.domain.repository

import kotlinx.coroutines.flow.Flow
import totersapp.marvel.task.domain.model.Comic
import totersapp.marvel.task.domain.model.Event
import totersapp.marvel.task.domain.model.Series
import totersapp.marvel.task.domain.model.Story
import totersapp.marvel.task.utils.DataState

interface CharacterDetailsRepository {
    suspend fun fetchComics(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Comic>>>

    suspend fun fetchEvents(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Event>>>

    suspend fun fetchSeries(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Series>>>

    suspend fun fetchStories(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Story>>>
}