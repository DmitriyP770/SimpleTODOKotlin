package com.example.simpletodokotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AddNoteVM(application: Application): AndroidViewModel(application) {

    private val db : NotesDataBase = NotesDataBase.getInstance(application)

    private var isDone = MutableLiveData<Boolean>()


    fun addNote(note: Note){
        var thread: Thread = Thread(
            Runnable {
                db.notesDAO().addNote(note)
                isDone.postValue(true)
            }

        )
        thread.start()
    }
    fun chekDoneness(): LiveData<Boolean>{
        return isDone
    }



}