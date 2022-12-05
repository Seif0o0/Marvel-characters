package totersapp.marvel.task.data.repository

import android.app.Application
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import totersapp.marvel.task.R
import totersapp.marvel.task.data.mapper.toComic
import totersapp.marvel.task.data.mapper.toEvent
import totersapp.marvel.task.data.mapper.toSeries
import totersapp.marvel.task.data.mapper.toStory
import totersapp.marvel.task.data.services.CharactersService
import totersapp.marvel.task.domain.model.Comic
import totersapp.marvel.task.domain.model.Event
import totersapp.marvel.task.domain.model.Series
import totersapp.marvel.task.domain.model.Story
import totersapp.marvel.task.domain.repository.CharacterDetailsRepository
import totersapp.marvel.task.utils.DataState
import totersapp.marvel.task.utils.handleStatusCode
import java.io.IOException
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val application: Application, private val service: CharactersService
) : CharacterDetailsRepository {

    override suspend fun fetchComics(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Comic>>> {
        return flow {
            try {
                emit(DataState.Loading())
                val response = service.fetchCharacterComics(characterId, map)
                if (response.isSuccessful) {
                    val data = response.body()!!.data
                    val comics = data.results.map { it.toComic() }
                    emit(DataState.Success(data = comics))
                } else {
                    emit(DataState.Error(message = handleStatusCode(application, response)))
                }
            } catch (e: IOException) {
                emit(DataState.Error(message = application.getString(R.string.no_internet_connection)))
            } catch (e: Exception) {
                Log.d("repositoryImpl", "Comics Error: ${e.message}")
                emit(DataState.Error("Error"))
            }
        }

    }

    override suspend fun fetchEvents(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Event>>> {
        return flow {
            try {
                emit(DataState.Loading())
                val response = service.fetchCharacterEvents(characterId, map)
                if (response.isSuccessful) {
                    val data = response.body()!!.data
                    val events = data.results.map { it.toEvent() }
                    emit(DataState.Success(data = events))
                } else {
                    emit(DataState.Error(message = handleStatusCode(application, response)))
                }
            } catch (e: IOException) {
                emit(DataState.Error(message = application.getString(R.string.no_internet_connection)))
            } catch (e: Exception) {
                Log.d("repositoryImpl", "Events Error: ${e.message}")
                emit(DataState.Error("Error"))
            }
        }

    }

    override suspend fun fetchSeries(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Series>>> {
        return flow {
            try {
                emit(DataState.Loading())
                val response = service.fetchCharacterSeries(characterId, map)
                if (response.isSuccessful) {
                    val data = response.body()!!.data
                    val series = data.results.map { it.toSeries() }
                    emit(DataState.Success(data = series))
                } else {
                    emit(DataState.Error(message = handleStatusCode(application, response)))
                }
            } catch (e: IOException) {
                emit(DataState.Error(message = application.getString(R.string.no_internet_connection)))
            } catch (e: Exception) {
                Log.d("repositoryImpl", "Series Error: ${e.message}")
                emit(DataState.Error("Error"))
            }
        }

    }

    override suspend fun fetchStories(
        characterId: String, map: Map<String, String>
    ): Flow<DataState<List<Story>>> {
        return flow {
            try {
                emit(DataState.Loading())
                val response = service.fetchCharacterStories(characterId, map)
                if (response.isSuccessful) {
                    val data = response.body()!!.data
                    val stories = data.results.map { it.toStory() }
                    emit(DataState.Success(data = stories))
                } else {
                    emit(DataState.Error(message = handleStatusCode(application, response)))
                }
            } catch (e: IOException) {
                emit(DataState.Error(message = application.getString(R.string.no_internet_connection)))
            } catch (e: Exception) {
                Log.d("repositoryImpl", "Stories Error: ${e.message}")
                emit(DataState.Error("Error"))
            }
        }
    }
}