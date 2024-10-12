package com.mhd.noteapp.add.domain

import com.mhd.noteapp.data.NoteEntity
import com.mhd.noteapp.data.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpsertUseCase(
    private val repository: NoteRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(noteEntity: NoteEntity) {
        withContext(coroutineDispatcher) {
            repository.upsertNote(noteEntity)
        }
    }

}