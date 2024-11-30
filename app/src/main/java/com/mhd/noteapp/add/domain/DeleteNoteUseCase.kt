package com.mhd.noteapp.add.domain

import com.mhd.noteapp.data.NoteEntity
import com.mhd.noteapp.data.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(noteEntity: NoteEntity) {
        withContext(dispatcher) {
            repository.deleteNote(noteEntity)
        }
    }
}