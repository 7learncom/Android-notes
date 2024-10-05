package com.mhd.noteapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.mhd.noteapp.data.AppDatabase
import com.mhd.noteapp.data.NoteRepository
import com.mhd.noteapp.data.NoteRepositoryImpl

interface AppContainer {

    fun noteRepository(): NoteRepository
}

class AppContainerImpl(
    private val context: Context,
) : AppContainer {

    companion object {
        private const val TAG = "AppContainer"
    }

    private val appDataBase: AppDatabase by lazy {
        Log.d(TAG, "AppDatabase: Initializing")
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    override fun noteRepository(): NoteRepository {
        return NoteRepositoryImpl(appDataBase.noteDao())
    }

}