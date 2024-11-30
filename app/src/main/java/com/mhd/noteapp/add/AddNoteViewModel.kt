package com.mhd.noteapp.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhd.noteapp.add.domain.DeleteNoteUseCase
import com.mhd.noteapp.add.domain.GetNoteUseCase
import com.mhd.noteapp.add.domain.UpsertUseCase
import com.mhd.noteapp.data.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val upsertUseCase: UpsertUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val args: AddNoteFragmentArgs,
) : ViewModel() {

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
            upsertUseCase(note)
            _onActionCompleteEvent.emit(Unit)
        }
    }

    fun onNoteDeleteClick() {
        viewModelScope.launch {
            deleteNoteUseCase(_currentNote.value!!)
            _onActionCompleteEvent.emit(Unit)
        }
    }

    private fun fetchCurrentNote() {
        if (isInEditMode) {
            viewModelScope.launch {
                _currentNote.value = getNoteUseCase(args.noteId)
            }
        }
    }

}