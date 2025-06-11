package com.example.myapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapp.data.datasource.SearchDataSource
import com.example.myapp.data.repository.SearchRepositoryImpl
import com.example.myapp.databinding.FragmentSearchBinding
import com.example.myapp.domain.usecase.SearchUseCase
import com.example.myapp.presentation.SearchViewModelFactory
import com.example.myapp.presentation.ui.adapter.SearchAdapter
import com.example.myapp.presentation.ui.state.SearchState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(
            SearchUseCase(
                SearchRepositoryImpl(SearchDataSource())
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SearchAdapter()
        binding.recyclerView.adapter = adapter

        // Настройка поиска с debounce
        binding.searchEditText.textChanges()
            .debounce(300)
            .distinctUntilChanged()
            .onEach { query ->
                viewModel.search(query.toString())
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        // Подписка на изменения состояния
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                when (state) {
                    SearchState.Idle -> {
                        binding.progressBar.isVisible = false
                        binding.errorTextView.isVisible = false
                        binding.emptyTextView.isVisible = false
                        adapter.submitList(emptyList())
                    }
                    SearchState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.errorTextView.isVisible = false
                        binding.emptyTextView.isVisible = false
                    }
                    is SearchState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.errorTextView.isVisible = false
                        binding.emptyTextView.isVisible = false
                        adapter.submitList(state.data)
                    }
                    is SearchState.Error -> {
                        binding.progressBar.isVisible = false
                        binding.errorTextView.isVisible = true
                        binding.errorTextView.text = state.message
                        binding.emptyTextView.isVisible = false
                        adapter.submitList(emptyList())
                    }
                    SearchState.Empty -> {
                        binding.progressBar.isVisible = false
                        binding.errorTextView.isVisible = false
                        binding.emptyTextView.isVisible = true
                        binding.emptyTextView.text = "Ничего не найдено"
                        adapter.submitList(emptyList())
                    }
                }
            }
        }
    }

    private fun EditText.textChanges(): Flow<CharSequence> {
        return callbackFlow {
            val watcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    trySend(s ?: "")
                }
            }
            addTextChangedListener(watcher)
            awaitClose { removeTextChangedListener(watcher) }
        }
    }
}