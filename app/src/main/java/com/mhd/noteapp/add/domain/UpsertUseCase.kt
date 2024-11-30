package com.mhd.noteapp.add.domain

import com.mhd.noteapp.data.NoteEntity
import com.mhd.noteapp.data.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpsertUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(noteEntity: NoteEntity) {
        withContext(coroutineDispatcher) {
            repository.upsertNote(noteEntity)
        }
    }

}