package totersapp.marvel.task.presentation.viewmodel

import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import totersapp.marvel.task.domain.model.*
import totersapp.marvel.task.domain.repository.CharacterDetailsRepository
import totersapp.marvel.task.utils.Constants
import totersapp.marvel.task.utils.DataState
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repo: CharacterDetailsRepository,
    private val state: SavedStateHandle
) : ViewModel() {
    private val map =
        mapOf(
            "limit" to "${Constants.SMALL_PAGE_SIZE}",
            "offset" to "0",
            "ts" to Constants.timeStamp.toString(),
            "apikey" to Constants.PUBLIC_API_KEY,
            "hash" to Constants.hash()
        )
    private val characterId = state.get<MarvelCharacter>("marvelCharacter")!!.id

    private val _comics = MutableStateFlow<List<Comic>>(emptyList())
    val comics: StateFlow<List<Comic>> get() = _comics

    private val _comicsLoadingState = MutableStateFlow(false)
    val comicsLoadingState: StateFlow<Boolean> get() = _comicsLoadingState

    private val _comicsErrorState = MutableStateFlow("")
    val comicsErrorState: StateFlow<String> get() = _comicsErrorState

    private val _comicsEmptyState = MutableStateFlow(View.INVISIBLE)
    val comicsEmptyState: StateFlow<Int> get() = _comicsEmptyState

    fun getComics() {
        viewModelScope.launch {
            repo.fetchComics(characterId = characterId, map = map).collectLatest { result ->
                when (result) {
                    is DataState.Success -> {
                        _comicsLoadingState.emit(false)
                        _comicsErrorState.emit("")
                        val data = result.data!!.toMutableList()
                        if (data.isEmpty()) {
                            _comicsEmptyState.emit(View.GONE)
                        } else {
                            _comicsEmptyState.emit(View.VISIBLE)
                            _comics.emit(data)
                        }
                    }
                    is DataState.Error -> {
                        _comicsLoadingState.emit(false)
                        _comicsErrorState.emit(result.message!!)
                    }
                    is DataState.Loading -> {
                        _comicsLoadingState.emit(true)
                        _comicsErrorState.emit("")
                    }
                }
            }
        }
    }

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> get() = _events

    private val _eventsLoadingState = MutableStateFlow(false)
    val eventsLoadingState: StateFlow<Boolean> get() = _eventsLoadingState

    private val _eventsErrorState = MutableStateFlow("")
    val eventsErrorState: StateFlow<String> get() = _eventsErrorState

    private val _eventsEmptyState = MutableStateFlow(View.INVISIBLE)
    val eventsEmptyState: StateFlow<Int> get() = _eventsEmptyState

    fun getEvents() {
        viewModelScope.launch {
            repo.fetchEvents(characterId = characterId, map = map).collectLatest { result ->
                when (result) {
                    is DataState.Success -> {
                        _eventsLoadingState.emit(false)
                        _eventsErrorState.emit("")
                        val data = result.data!!.toMutableList()
                        if (data.isEmpty()) {
                            _eventsEmptyState.emit(View.GONE)
                        } else {
                            _eventsEmptyState.emit(View.VISIBLE)
                            _events.emit(data)
                        }
                    }
                    is DataState.Error -> {
                        _eventsLoadingState.emit(false)
                        _eventsErrorState.emit(result.message!!)
                    }
                    is DataState.Loading -> {
                        _eventsLoadingState.emit(true)
                        _eventsErrorState.emit("")
                    }
                }
            }
        }
    }

    private val _series = MutableStateFlow<List<Series>>(emptyList())
    val series: StateFlow<List<Series>> get() = _series

    private val _seriesLoadingState = MutableStateFlow(false)
    val seriesLoadingState: StateFlow<Boolean> get() = _seriesLoadingState

    private val _seriesErrorState = MutableStateFlow("")
    val seriesErrorState: StateFlow<String> get() = _seriesErrorState

    private val _seriesEmptyState = MutableStateFlow(View.INVISIBLE)
    val seriesEmptyState: StateFlow<Int> get() = _seriesEmptyState

    fun getSeries() {
        viewModelScope.launch {
            repo.fetchSeries(characterId = characterId, map = map).collectLatest { result ->
                when (result) {
                    is DataState.Success -> {
                        _seriesLoadingState.emit(false)
                        _seriesErrorState.emit("")
                        val data = result.data!!.toMutableList()
                        if (data.isEmpty()) {
                            _seriesEmptyState.emit(View.GONE)
                        } else {
                            _seriesEmptyState.emit(View.VISIBLE)
                            _series.emit(data)
                        }
                    }
                    is DataState.Error -> {
                        _seriesLoadingState.emit(false)
                        _seriesErrorState.emit(result.message!!)
                    }
                    is DataState.Loading -> {
                        _seriesLoadingState.emit(true)
                        _seriesErrorState.emit("")
                    }
                }
            }
        }
    }

    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> get() = _stories

    private val _storiesLoadingState = MutableStateFlow(false)
    val storiesLoadingState: StateFlow<Boolean> get() = _storiesLoadingState

    private val _storiesErrorState = MutableStateFlow("")
    val storiesErrorState: StateFlow<String> get() = _storiesErrorState

    private val _storiesEmptyState = MutableStateFlow(View.INVISIBLE)
    val storiesEmptyState: StateFlow<Int> get() = _storiesEmptyState

    fun getStories() {
        viewModelScope.launch {
            delay(5000)
            repo.fetchStories(characterId = characterId, map = map).collectLatest { result ->
                when (result) {
                    is DataState.Success -> {
                        _storiesLoadingState.emit(false)
                        _storiesErrorState.emit("")
                        val data = result.data!!.toMutableList()
                        if (data.isEmpty()) {
                            _storiesEmptyState.emit(View.GONE)
                        } else {
                            _storiesEmptyState.emit(View.VISIBLE)
                            _stories.emit(data)
                        }
                    }
                    is DataState.Error -> {
                        _storiesLoadingState.emit(false)
                        _storiesErrorState.emit(result.message!!)
                    }
                    is DataState.Loading -> {
                        _storiesLoadingState.emit(true)
                        _storiesErrorState.emit("")
                    }
                }
            }
        }
    }

    init {
        getComics()
        getEvents()
        getSeries()
        getStories()
    }
}