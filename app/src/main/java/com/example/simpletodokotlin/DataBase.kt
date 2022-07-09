package com.example.simpletodokotlin

import java.util.*
import kotlin.collections.ArrayList

class DataBase private constructor(){

    private var notes: ArrayList<Note> = ArrayList()

 // because of impossibility of usage methods in constructor in kotlin i must use methods in init bloc........strange.....
init {
    initNotesList()
}
    fun addNote(note: Note){
        notes.add(note)
    }

     fun getNotes(): ArrayList<Note>{
        return notes
    }

    private fun initNotesList(){
        val random : Random =  Random()
        for(i:Int in 1..30) {
            notes.add(Note(i,"Note $i",random.nextInt(3)))
        }
    }
    fun removeNote(noteId:Int){
        notes.removeAt(noteId)
    }

   companion object {
       private var instance: DataBase? = null

       fun getInstance() : DataBase{
           if (instance == null) {
               instance = DataBase()
               return instance as DataBase
           }
           else
               return instance as DataBase
       }
   }

}