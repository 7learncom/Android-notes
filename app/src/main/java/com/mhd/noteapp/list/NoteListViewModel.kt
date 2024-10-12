package com.mhd.noteapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.noteapp.NoteApplication
import com.mhd.noteapp.list.domain.GetNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class NoteListViewModel(
    private val useCase: GetNotesUseCase,
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as NoteApplication
                val repository = application.appContainer.noteRepository()

                val useCase = GetNotesUseCase(
                    noteRepository = repository,
                    coroutineDispatcher = Dispatchers.IO
                )

                NoteListViewModel(
                    useCase = useCase,
                )
            }
        }
    }

    private val searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val notes = searchQuery
        .flatMapLatest(useCase::invoke)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
    )

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

}