package com.mhd.noteapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.mhd.noteapp.data.AppDatabase
import com.mhd.noteapp.data.NoteDao

interface AppContainer {

    fun noteDao(): NoteDao

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

    override fun noteDao(): NoteDao = appDataBase.noteDao()

}