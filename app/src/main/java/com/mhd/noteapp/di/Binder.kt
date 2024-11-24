package com.mhd.noteapp.di

import com.mhd.noteapp.data.NoteRepository
import com.mhd.noteapp.data.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface Binder {

    @Binds
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository

}