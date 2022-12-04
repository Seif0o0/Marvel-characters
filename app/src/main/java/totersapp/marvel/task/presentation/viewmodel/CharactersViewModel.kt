package totersapp.marvel.task.presentation.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import totersapp.marvel.task.domain.model.MarvelCharacter
import totersapp.marvel.task.domain.repository.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repo: CharactersRepository
) : ViewModel() {

    val characters: Flow<PagingData<MarvelCharacter>> =
        repo.fetchCharacters().cachedIn(viewModelScope)

    private val _screenSizeState = MutableStateFlow(Pair(0, 0))
    val screenSizeState: StateFlow<Pair<Int, Int>> get() = _screenSizeState

    fun screenSizeState(value: Pair<Int, Int>) {
        _screenSizeState.value = value
    }

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> get() = _errorState

    private val _emptyState = MutableStateFlow(View.GONE)
    val emptyState: StateFlow<Int> get() = _emptyState

    fun handleLoadStateListener(combinedLoadStates: CombinedLoadStates, itemCount: Int) {
        // Handle empty list state
        if (combinedLoadStates.refresh is LoadState.NotLoading) {
            _emptyState.value = if (itemCount == 0) View.VISIBLE
            else View.GONE
        }

        // Handle loading state
        _loadingState.value = combinedLoadStates.refresh is LoadState.Loading

        //  Handel error state
        if (combinedLoadStates.refresh is LoadState.Error) {
            val error = (combinedLoadStates.refresh as LoadState.Error).error.localizedMessage!!
            _errorState.value = error
        } else {
            _errorState.value = ""
        }
    }


}