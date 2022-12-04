package totersapp.marvel.task.data.paging

import android.app.Application
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import totersapp.marvel.task.R
import totersapp.marvel.task.data.mapper.toMarvelCharacter
import totersapp.marvel.task.data.services.CharactersService
import totersapp.marvel.task.domain.model.MarvelCharacter
import totersapp.marvel.task.utils.Constants
import totersapp.marvel.task.utils.handleStatusCode
import java.io.IOException
import javax.inject.Inject

class MarvelCharactersPagingSource @Inject constructor(
    private val application: Application,
    private val service: CharactersService
) : PagingSource<Int, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val currentPage = params.key ?: 0
        return try {
            val map = mutableMapOf<String, String>()
            map["limit"] = Constants.PAGE_SIZE.toString()
            map["ts"] = Constants.timeStamp.toString()
            map["offset"] = (currentPage * (Constants.PAGE_SIZE)).toString()
            map["apikey"] = Constants.PUBLIC_API_KEY
            map["hash"] = Constants.hash()
            val response = service.fetchCharacters(map)
            if (response.isSuccessful) {
                val body = response.body()!!
                val data = body.data
                val offset = data.offset
                val count = data.count
                val characters = data.results.map { it.toMarvelCharacter() }

                LoadResult.Page(
                    data = characters,
                    prevKey = if (offset == 0) null else (offset / Constants.PAGE_SIZE) - 1,
                    nextKey = if (count < 10) null else (offset / Constants.PAGE_SIZE) + 1
                )
            } else {
                LoadResult.Error(Exception(handleStatusCode(application, response)))
            }
        } catch (e: IOException) {
            LoadResult.Error(Exception(application.getString(R.string.no_internet_connection)))
        } catch (e: Exception) {
            Log.d("PagingSource", "Source: $e")
            LoadResult.Error(Exception(e.message!!))
        }
    }
}