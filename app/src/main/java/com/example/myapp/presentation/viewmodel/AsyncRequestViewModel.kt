package com.example.myapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.FetchDataUseCase
import com.example.myapp.presentation.state.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AsyncRequestViewModel(
    private val useCase: FetchDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DataState>(DataState.Idle)
    val state: StateFlow<DataState> = _state

    fun loadData() {
        _state.value = DataState.Loading

        viewModelScope.launch {
            try {
                val result = useCase.execute()
                _state.value = DataState.Success(result)
            } catch (e: Exception) {
                _state.value = DataState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}