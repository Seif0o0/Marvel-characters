package totersapp.marvel.task.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import totersapp.marvel.task.data.paging.MarvelCharactersPagingSource
import totersapp.marvel.task.domain.model.MarvelCharacter
import totersapp.marvel.task.domain.repository.CharactersRepository
import totersapp.marvel.task.utils.Constants
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor() : CharactersRepository {

    @Inject
    lateinit var pagingResource: MarvelCharactersPagingSource

    override fun fetchCharacters(): Flow<PagingData<MarvelCharacter>> {
        Log.d("Impl", "FetchCharacters")
        return Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)) {
            pagingResource
        }.flow
    }

}