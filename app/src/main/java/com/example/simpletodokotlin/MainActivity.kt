package com.example.simpletodokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private var db: DataBase = DataBase.getInstance()
    private val notesAdapter = NotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        rvNotes.adapter = notesAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)

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
                    db.removeNote(position)
                    showNotes()

                }
            })
        mIth.attachToRecyclerView(rvNotes)


    }

    override fun onResume() {
        showNotes()

        super.onResume()

    }

    private fun initViews(){
        rvNotes = findViewById(R.id.rvNotes)
        buttonAdd = findViewById(R.id.floatingActionButton)
    }
    private fun showNotes() {
        notesAdapter.updateNotes(db.getNotes())
    }

}
//Itemtouchhelper
//vh.getposition
