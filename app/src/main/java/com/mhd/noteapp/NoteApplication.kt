package com.mhd.noteapp

import android.app.Application

class NoteApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(context = this)
    }

}