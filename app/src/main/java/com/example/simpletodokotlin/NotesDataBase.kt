package com.example.simpletodokotlin

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 3, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {

    companion object{
        val NAME : String = "DataBase"
        @Volatile private var instance: NotesDataBase? = null
        fun getInstance(context: Context): NotesDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

//        fun getInstance(appContext: Application): NotesDataBase {
//            if (instance == null){
//                instance = Room.databaseBuilder(
//                    appContext,
//                    NotesDataBase::class.java,
//                    NAME
//
//                ).allowMainThreadQueries().build()
//
//                return instance as NotesDataBase
//            }
//            return instance as NotesDataBase
//        }

        private fun buildDatabase(context: Context): NotesDataBase{
            return  Room.databaseBuilder(context,  NotesDataBase::class.java, NAME).build()

        }
    }
    abstract fun notesDAO(): NotesDAO




}