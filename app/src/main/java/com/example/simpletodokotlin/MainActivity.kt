package com.example.simpletodokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var  linearLayout: LinearLayout
    private lateinit var buttonAdd: FloatingActionButton
    private var notes: MutableList<Note> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

       val random : Random =  Random()
        for(i:Int in 1..20) {
            notes.add(Note(i,"Note $i",random.nextInt(3)))
     }
        showNotes()


    }
    private fun initViews(){
        linearLayout = findViewById(R.id.linearLayoutNotes)
        buttonAdd = findViewById(R.id.floatingActionButton)
    }
    private fun showNotes() {
    for (note:Note in notes){
        val view:View = layoutInflater.inflate(R.layout.note_item, linearLayout, false)
       var textViewNote: TextView = view.findViewById(R.id.textViewNote)
        textViewNote.setText(note.text)

       var colorId:Int
       when(note.priority){
           0 -> colorId= android.R.color.holo_green_light
           1 -> colorId = android.R.color.holo_orange_light
           else ->{
              colorId =  android.R.color.holo_red_light
           }
       }
       var color:Int = ContextCompat.getColor(this, colorId)
        textViewNote.setTextColor(color)
        linearLayout.addView(view)

        }
    }
}