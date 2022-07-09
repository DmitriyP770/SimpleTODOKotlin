package com.example.simpletodokotlin

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvNotes: RecyclerView
    private lateinit var buttonAdd: FloatingActionButton
//    private lateinit var noteDB:NotesDataBase

//    private lateinit var db: DataBase
    private val notesAdapter = NotesAdapter()
    //handler - is for UI thread interactions
    private var handler:Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        noteDB = NotesDataBase.getInstance(application)
        initViews()
         var viewModel: MainActivityViewModel = MainActivityViewModel(application)

        rvNotes.adapter = notesAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)
        viewModel.data.observe(this, androidx.lifecycle.Observer {
            it -> notesAdapter.updateNotes(it)
        })
//        noteDB.notesDAO().getNotes().observe(this, androidx.lifecycle.Observer {
//            it -> notesAdapter.updateNotes(it)
//
//        })

        buttonAdd.setOnClickListener(View.OnClickListener {
          Intent(this, AddNoteActivity::class.java).also { startActivity(it) }
        })
        var mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
                ): Boolean {
                    val fromPos = viewHolder.adapterPosition
                    val toPos = target.adapterPosition
                    // move item in `fromPos` to `toPos` in adapter.
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var position = viewHolder.adapterPosition
                    viewModel.deleteNote(notesAdapter.getNotes(position))

//                    var thread :Thread = Thread(Runnable {
//                        noteDB.notesDAO().deleteNote(notesAdapter.getNotes(position).id)
////                        handler.post(Runnable {
////                            showNotes()
////
////                        })
//
//                    })
//                    thread.start()

                }
            })
        mIth.attachToRecyclerView(rvNotes)


    }


private fun showNotes(){

}
    private fun initViews(){
        rvNotes = findViewById(R.id.rvNotes)
        buttonAdd = findViewById(R.id.floatingActionButton)
    }
//    private fun showNotes() {
//
//        var thread:Thread = Thread(Runnable {
//            var notes: List<Note> = noteDB.notesDAO().getNotes()
//            handler.post(Runnable {
//                notesAdapter.updateNotes(notes)
//            })
//
//        })
//thread.start()
//    }

}
//Itemtouchhelper
//vh.getposition
