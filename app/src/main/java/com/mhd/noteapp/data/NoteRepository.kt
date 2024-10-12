package com.mhd.noteapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

interface NoteRepository {

    fun getAllNotes(): Flow<List<NoteEntity>>

    fun searchNoteByTitle(query: String): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Int): NoteEntity

    suspend fun upsertNote(noteEntity: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)

}

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun getNoteById(id: Int): NoteEntity = withContext(Dispatchers.IO) {
            noteDao.getNoteById(id)
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

    override fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAll()

    override fun searchNoteByTitle(query: String): Flow<List<NoteEntity>> {
        return noteDao.searchNoteByTitle(query)
    }

}