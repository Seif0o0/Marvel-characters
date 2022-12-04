package totersapp.marvel.task.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import totersapp.marvel.task.domain.model.MarvelCharacter

interface CharactersRepository {
    fun fetchCharacters(): Flow<PagingData<MarvelCharacter>>
}