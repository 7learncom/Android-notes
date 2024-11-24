package com.mhd.noteapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NoteRepository {

    fun getAllNotes(): Flow<List<NoteEntity>>

    fun searchNoteByTitle(query: String): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Int): NoteEntity

    suspend fun upsertNote(noteEntity: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)

}

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {


    override suspend fun getNoteById(id: Int): NoteEntity = noteDao.getNoteById(id)


    override suspend fun upsertNote(noteEntity: NoteEntity) {
        noteDao.upsert(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.delete(noteEntity)
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAll()

    override fun searchNoteByTitle(query: String): Flow<List<NoteEntity>> {
        return noteDao.searchNoteByTitle(query)
    }

}