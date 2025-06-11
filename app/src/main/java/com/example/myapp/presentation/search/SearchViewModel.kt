package com.example.myapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.model.SearchResult
import com.example.myapp.domain.usecase.SearchUseCase
import com.example.myapp.presentation.ui.state.SearchState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchState: StateFlow<SearchState> = _searchState

    private var currentSearchJob: Job? = null

    fun search(query: String) {
        currentSearchJob?.cancel()

        if (query.isEmpty()) {
            _searchState.value = SearchState.Idle
            return
        }

        _searchState.value = SearchState.Loading

        currentSearchJob = viewModelScope.launch {
            when (val result = searchUseCase(query)) {
                is SearchResult.Success -> {
                    _searchState.value = SearchState.Success(result.data)
                }
                is SearchResult.Error -> {
                    _searchState.value = SearchState.Error(result.message)
                }
                SearchResult.Empty -> {
                    _searchState.value = SearchState.Empty
                }
            }
        }
    }
}