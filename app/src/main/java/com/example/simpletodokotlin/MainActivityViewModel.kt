package com.example.simpletodokotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var db : NotesDataBase = NotesDataBase.getInstance(application)

    init {
        getData()
    }

   lateinit var data: LiveData<List<Note>>

   fun getData(){
        var thread : Thread = Thread( Runnable {
          data =   db.notesDAO().getNotes()
        })
        thread.start()
    }

    fun deleteNote(note: Note){
         var thread : Thread = Thread(Runnable {
             db.notesDAO().deleteNote(note.id)
         })
        thread.start()
    }

}