package com.mhd.noteapp.di

import android.content.Context
import androidx.room.Room
import com.mhd.noteapp.data.AppDatabase
import com.mhd.noteapp.data.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNoteDao(appDataBase: AppDatabase): NoteDao = appDataBase.noteDao()

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app-database"
    ).build()

}