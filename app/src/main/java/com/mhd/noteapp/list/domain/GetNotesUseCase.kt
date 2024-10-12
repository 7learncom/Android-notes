package com.mhd.noteapp.list.domain

import com.mhd.noteapp.data.NoteEntity
import com.mhd.noteapp.data.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetNotesUseCase(
    private val noteRepository: NoteRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(searchQuery: String): Flow<List<NoteEntity>> {
        return if (searchQuery.isBlank()) {
            noteRepository.getAllNotes()
        } else {
            noteRepository.searchNoteByTitle(searchQuery)
        }.flowOn(coroutineDispatcher)
    }


}