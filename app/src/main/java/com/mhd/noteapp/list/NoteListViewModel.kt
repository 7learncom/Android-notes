package com.mhd.noteapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.noteapp.NoteApplication
import com.mhd.noteapp.data.NoteDao

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
}