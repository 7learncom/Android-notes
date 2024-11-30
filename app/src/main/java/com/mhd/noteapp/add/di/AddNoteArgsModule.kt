package com.mhd.noteapp.add.di

import androidx.lifecycle.SavedStateHandle
import com.mhd.noteapp.add.AddNoteFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AddNoteArgsModule {

    @Provides
    fun provideAddNoteArgs(savedStateHandle: SavedStateHandle): AddNoteFragmentArgs =
        AddNoteFragmentArgs.fromSavedStateHandle(savedStateHandle)

}