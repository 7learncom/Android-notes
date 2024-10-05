package com.mhd.noteapp.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.noteapp.NoteApplication
import com.mhd.noteapp.data.NoteEntity
import com.mhd.noteapp.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddNoteViewModel(
    private val noteRepository: NoteRepository,
    private val args: AddNoteFragmentArgs,
) : ViewModel() {

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NoteApplication
                val repository = app.appContainer.noteRepository()

                val savedStateHandle = createSavedStateHandle()
                val args = AddNoteFragmentArgs.fromSavedStateHandle(savedStateHandle)

                return@initializer AddNoteViewModel(
                    noteRepository = repository,
                    args = args
                )
            }
        }
    }

    val isInEditMode: Boolean
        get() = args.noteId != -1

    private val _currentNote = MutableStateFlow<NoteEntity?>(null)
    val currentNote = _currentNote.asStateFlow()

    private val _onActionCompleteEvent = MutableSharedFlow<Unit>()
    val onActionCompleteEvent = _onActionCompleteEvent.asSharedFlow()

    init {
        fetchCurrentNote()
    }

    fun onActionClick(title: String, text: String) {

        val note = _currentNote.value?.copy(
            title = title,
            text = text
        ) ?: NoteEntity(title = title, text = text)

        viewModelScope.launch {
            noteRepository.upsertNote(note)
            _onActionCompleteEvent.emit(Unit)
        }
    }

    fun onNoteDeleteClick() {
        viewModelScope.launch {
            noteRepository.deleteNote(_currentNote.value!!)
            _onActionCompleteEvent.emit(Unit)
        }
    }

    private fun fetchCurrentNote() {
        if (isInEditMode) {
            viewModelScope.launch {
                _currentNote.value = noteRepository.getNoteById(args.noteId)
            }
        }
    }

}