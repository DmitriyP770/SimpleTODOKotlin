package com.example.simpletodokotlin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :noteID")
    fun deleteNote(noteID: Int)

}