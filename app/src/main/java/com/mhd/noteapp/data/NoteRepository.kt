package com.mhd.noteapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

interface NoteRepository {

    fun getNotes(searchQuery: String): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Int): NoteEntity

    suspend fun upsertNote(noteEntity: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)

//    fun getAllNotes(): Flow<NoteEntity>
//
//    fun searchNote(query: String): Flow<NoteEntity>

}

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getNotes(searchQuery: String): Flow<List<NoteEntity>> {
        return if (searchQuery.isBlank()) {
            noteDao.getAll()
        } else {
            noteDao.searchNoteByTitle(searchQuery)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getNoteById(id: Int): NoteEntity {
        return withContext(Dispatchers.IO) {
            noteDao.getNoteById(id)
        }
    }

    override suspend fun upsertNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            noteDao.upsert(noteEntity)
        }
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            noteDao.delete(noteEntity)
        }
    }

}