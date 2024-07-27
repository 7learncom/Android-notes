package com.mhd.noteapp.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.noteapp.NoteApplication
import com.mhd.noteapp.data.NoteDao

class AddNoteViewModel(
    private val noteDao: NoteDao,
    private val args: AddNoteFragmentArgs,
) : ViewModel() {

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NoteApplication
                val dao = app.appContainer.noteDao()

                val savedStateHandle = createSavedStateHandle()
                val args = AddNoteFragmentArgs.fromSavedStateHandle(savedStateHandle)

                return@initializer AddNoteViewModel(noteDao = dao, args = args)
            }
        }
    }

    val isInEditMode: Boolean
        get() = args.noteId != -1

}