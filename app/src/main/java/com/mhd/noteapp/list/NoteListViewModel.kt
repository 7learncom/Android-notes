package com.mhd.noteapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.noteapp.NoteApplication
import com.mhd.noteapp.data.NoteDao
import com.mhd.noteapp.data.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val noteDao: NoteDao,
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as NoteApplication
                NoteListViewModel(
                    noteDao = application.appContainer.noteDao()
                )
            }
        }
    }

    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            val notes = noteDao.getAll()
            _notes.value = notes
        }
    }
}