package com.mhd.noteapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val text: String,
)
